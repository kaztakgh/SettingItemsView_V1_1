/**
 * @file HeaderItem.kt
 */
package io.github.kaztakgh.settingitemsviewlibrary

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

/**
 * ヘッダーの作成
 *
 * @property title タイトル(省略不可)
 * @property tag タグ(識別子、省略不可)
 */
@SuppressLint("ParcelCreator")
data class HeaderItem(
    override val title: String,
    override val tag: String
) : ItemInterface, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() as String,
        parcel.readString() as String
    )

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     * May be 0 or [.PARCELABLE_WRITE_RETURN_VALUE].
     */
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeString(title)
        dest.writeString(tag)
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of [.writeToParcel],
     * the return value of this method must include the
     * [.CONTENTS_FILE_DESCRIPTOR] bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HeaderItem> {
        override fun createFromParcel(parcel: Parcel): HeaderItem {
            return HeaderItem(parcel)
        }

        override fun newArray(size: Int): Array<HeaderItem?> {
            return arrayOfNulls(size)
        }
    }
}