package com.androidLearning.flowtest2opfuction

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking


@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
fun main() {

    runBlocking {


        // =========================================================================================
        // 1.map
        // =========================================================================================
        val flow = flowOf(1, 2, 3, 4, 5)
        flow.map {
            it * it
        }.collect {
            print(it)
        }

        // =========================================================================================
        // 2.filter
        // =========================================================================================
        flow.filter {
            it % 2 == 0
        }.map {
            it * it
        }.collect {
            print(it)
        }

        // =========================================================================================
        // 3.onEach
        // =========================================================================================
        flow.filter {
            it % 2 == 0
        }.onEach {
            print(it)
        }.map {
            it * it
        }.collect {
            print(it)
        }

        // =========================================================================================
        // 4.debounce 去抖函数，debounce函数可以用来确保flow的各项数据之间存在一定的时间间隔，如果是时间点过于临近的数据只会保留最后一条。
        // =========================================================================================
        flow {
            emit(1)
            emit(2)
            delay(600)

            emit(3)
            delay(100)

            emit(4)
            delay(100)

            emit(5)
        }
            // 只有间隔时间超过 500 毫秒的时间还会被收集到，否则会被后面的数据覆盖刷新
            .debounce(500).collect {
                print(it) // 2, 5
            }

        // =========================================================================================
        // 5.sample 采样函数，sample是采样的意思，也就是说，它可以从flow的数据流当中按照一定的时间间隔来采样某一条数据。
        // =========================================================================================
        flow {
            while (true) {
                emit("发送一条数据")
            }
        }
            // 间隔时间 1s，取一条弹幕数据
            .sample(1000)
            .flowOn(Dispatchers.IO)
            .collect {
                print(it)
            }

        // =========================================================================================
        // 终止符函数
        // 6.reduce 累加函数：其中acc是累积值的意思，value则是当前值的意思
        // flow.reduce { acc, value -> acc + value }
        // =========================================================================================
        val result = flow {
            for (i in 1..100) {
                emit(i)
            }
        }
            .reduce { acc, value -> acc + value }

        print(result) // 5050

        // =========================================================================================
        // 终止符函数
        // 7.fold 带初始值的累加函数：功能与上面的 reduce 函数一样
        // =========================================================================================
        flow {
            for (ch in 'A'..'Z') {
                emit(ch)
            }
        }
            .fold("Alphabet：") { acc, value -> acc + value }


        // =========================================================================================
        // flatMap 系列函数, flatMap的核心，就是将两个flow中的数据进行映射、合并、压平成一个flow，最后再进行输出。
        //
        // 8.flatMapConcat
        // =========================================================================================
        flowOf(1, 2, 3)
            .flatMapConcat {
                flowOf("${it}a", "${it}b")
            }
            .collect {
                println(it)
                // 1a
                // 1b
                // 2a
                // 2b
                // 3a
                // 3b
            }


        // =========================================================================================
        // 9.flatMapMerge
        // 效果与 flatMapMerge 一样，区别在于：flatMapMerge 是并发的并不保证顺序，而前者是有序的
        // concat是连接的意思，merge是合并的意思。连接一定会保证数据是按照原有的顺序连接起来的，而合并则只保证将数据合并到一起，并不会保证顺序。
        // 因此，flatMapMerge函数的内部是启用并发来进行数据处理的，它不会保证最终结果的顺序。
        // =========================================================================================
        flowOf(300, 200, 100)
            .flatMapMerge {
                flow {
                    delay(it.toLong())
                    emit("${it}A")
                    emit("${it}B")
                }
            }
            .collect {
                println(it)
                // 100A
                // 100B
                // 200A
                // 200B
                // 300A
                // 300B
            }

        // =========================================================================================
        // 10.flatMapLatest
        // flatMapLatest函数也是类似的，flow1中的数据传递到flow2中会立刻进行处理，但如果flow1中的下一个数据要发送了，而flow2中上一个数据还没处理完，则会直接将剩余逻辑取消掉，开始处理最新的数据。
        // =========================================================================================
        flow {
            emit(1)
            delay(150)

            emit(2)
            delay(50)

            emit(3)
        }.flatMapLatest {
            flow {
                delay(100)
                emit("$it")
            }
        }.collect {
            println(it)
            // 1
            // 3
        }


        // =========================================================================================
        // 11.zip
        // 用于连接两个 flow ，两个 flow 是并行的关系
        // zip函数的规则是，只要其中一个flow中的数据全部处理结束就会终止运行，剩余未处理的数据将不会得到处理。因此，flow2中的4和5这两个数据会被舍弃掉
        // =========================================================================================
        val zipFlow1 = flowOf("a", "b", "c")
        val zipFlow2 = flowOf(1, 2, 3, 4, 5)
        zipFlow1.zip(zipFlow2) { a, b ->
            a + b
        }.collect {
            println(it)
            // a1
            // b2
            // c3
        }

        // =========================================================================================
        // 背压三剑客
        // 12.buffer
        // =========================================================================================

        // 因为默认情况下，collect函数和flow函数会运行在同一个协程当中，因此collect函数中的代码没有执行完，flow函数中的代码也会被挂起等待。
        flow {
            emit(1)
            delay(1000)
            emit(2)
            delay(1000)
            emit(3)
        }.onEach {
            println("$it is ready")
        }.collect {
            delay(1000)
            println("$it is handled")
        }

        // 改用 buffer 来改善这个问题
        flow {
            emit(1)
            delay(1000)
            emit(2)
            delay(1000)
            emit(3)
        }
            .onEach {
                println("$it is ready")
            }
            .buffer()
            .collect {
                delay(1000)
                println("$it is handled")
            }

        // =========================================================================================
        // 背压三剑客
        // 13.conflate
        // 我的第一直觉是，当前正在处理的数据无论如何都应该处理完，然后准备去处理下一条数据时，直接处理最新的数据即可，中间的数据就都可以丢弃掉了。
        // =========================================================================================
        flow {
            var count = 0
            while (true) {
                emit(count)
                delay(1000)
                count++
            }
        }
            .conflate()
            .collect {
                println("start handle $it")
                delay(2000)
                println("finish handle $it")
            }
    }
}