/**
 * @file SwitchItem.kt
 */
package io.github.kaztakgh.settingitemsviewlibrary

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable

/**
 * 状態をON/OFFで指定するアイテムの作成
 *
 * @property title タイトル(省略不可)
 * @property tag タグ(識別子、省略不可)
 * @property bmpIcon 左側に表示するアイコン(省略時null)
 * @property enabled 選択可能であるか(省略時true)
 * @property checked チェック(省略時false)
 * @property textOnTrue ONの時に表示するテキスト(省略時ON)
 * @property textOnFalse OFFの時に表示するテキスト(省略時OFF)
 */
data class SwitchItem(
    override val title: String,
    override val tag: String,
    val bmpIcon: Bitmap? = null,
    var enabled: Boolean = true,
    var checked: Boolean = false,
    val textOnTrue: String = SwitchItemViewHolder.STATE_ON_TEXT_DEFAULT,
    val textOnFalse: String = SwitchItemViewHolder.STATE_OFF_TEXT_DEFAULT
) : ItemInterface, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() as String,
        parcel.readString() as String,
        parcel.readParcelable(Bitmap::class.java.classLoader),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString() as String,
        parcel.readString() as String
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(tag)
        parcel.writeParcelable(bmpIcon, flags)
        parcel.writeByte(if (enabled) 1 else 0)
        parcel.writeByte(if (checked) 1 else 0)
        parcel.writeString(textOnTrue)
        parcel.writeString(textOnFalse)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SwitchItem> {
        override fun createFromParcel(parcel: Parcel): SwitchItem {
            return SwitchItem(parcel)
        }

        override fun newArray(size: Int): Array<SwitchItem?> {
            return arrayOfNulls(size)
        }
    }

    /**
     * @since v1.2.0
     * アイテムの状態が変更されたときの処理
     * SettingItemsAdapterで使用する
     * 定義はActivity/Fragmentで行う
     */
    interface OnItemStateChangeListener {
        /**
         * スイッチのチェックが変更されたときの処理
         *
         * @param adapter [SettingItemsAdapter]
         * @param checked スイッチの状態
         */
        fun onSwitchCheckChange(
            adapter: SettingItemsAdapter,
            checked: Boolean
        )
    }
    var stateChangedListener: OnItemStateChangeListener? = null
}