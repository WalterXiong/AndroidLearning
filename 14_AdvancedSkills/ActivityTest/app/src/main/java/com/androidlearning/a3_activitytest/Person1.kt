package com.androidlearning.a3_activitytest

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Person1(var name: String, var age: Int) : Parcelable

// 普通写法
/*class Person1 : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeInt(age)
    }

    companion object CREATOR : Parcelable.Creator<Person1> {

        override fun createFromParcel(source: Parcel?): Person1? {
            val person1 = Person1()
            source?.let {
                person1.name = source.readString() ?: ""
                person1.age = source.readInt()
            }
            return person1
        }

        override fun newArray(size: Int): Array<out Person1?>? {
            return arrayOfNulls<Person1>(size)
        }
    }
}*/
