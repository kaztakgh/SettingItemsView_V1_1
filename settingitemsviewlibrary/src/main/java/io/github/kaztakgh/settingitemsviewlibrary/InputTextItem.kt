/**
 * @file InputTextItem.kt
 */
package io.github.kaztakgh.settingitemsviewlibrary

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import android.text.InputType

/**
 * テキストを入力するアイテムの作成
 *
 * @property title タイトル(省略不可)
 * @property tag タグ(識別子、省略不可)
 * @property bmpIcon 左側に表示するアイコン(省略時null)
 * @property enabled 選択可能であるか(省略時true)
 * @property text テキスト
 * @property length テキストの長さ(省略時はViewHolderで指定した長さを利用する)
 * @property inputType 入力可能な文字列の形式(InputTypeクラスの定数を利用すること)
 */
data class InputTextItem(
    override val title: String,
    override val tag: String,
    val bmpIcon: Bitmap? = null,
    var enabled: Boolean = true,
    var text: String = "",
    val length: Int = InputTextItemViewHolder.INIT_LENGTH,
    val inputType: Int = InputType.TYPE_CLASS_TEXT
) : ItemInterface, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() as String,
        parcel.readString() as String,
        parcel.readParcelable(Bitmap::class.java.classLoader),
        parcel.readByte() != 0.toByte(),
        parcel.readString() as String,
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(tag)
        parcel.writeParcelable(bmpIcon, flags)
        parcel.writeByte(if (enabled) 1 else 0)
        parcel.writeString(text)
        parcel.writeInt(length)
        parcel.writeInt(inputType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InputTextItem> {
        override fun createFromParcel(parcel: Parcel): InputTextItem {
            return InputTextItem(parcel)
        }

        override fun newArray(size: Int): Array<InputTextItem?> {
            return arrayOfNulls(size)
        }
    }

    /**
     * テキストを変更したときの処理
     */
    interface OnTextChangedListener {
        /**
         * テキストを変更したときの処理
         *
         * @param text 入力されたテキスト
         */
        fun onTextChanged(text: String)
    }
    var textChangedListener: OnTextChangedListener? = null
}