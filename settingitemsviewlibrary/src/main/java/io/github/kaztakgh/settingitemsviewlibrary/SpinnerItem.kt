/**
 * @file SpinnerItem.kt
 */
package io.github.kaztakgh.settingitemsviewlibrary

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable

/**
 * Spinnerで状態を指定するアイテムの作成
 *
 * @property title タイトル(省略不可)
 * @property tag タグ(識別子、省略不可)
 * @property bmpIcon 左側に表示するアイコン(省略時null)
 * @property enabled 選択可能であるか(省略時true)
 * @property options 選択肢一覧(省略不可)
 * @property select 初期選択位置(省略時0)
 */
data class SpinnerItem(
    override val title: String,
    override val tag: String,
    val bmpIcon: Bitmap? = null,
    var enabled: Boolean = true,
    val options: Array<String>,
    var select: Int = 0
) : ItemInterface, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() as String,
        parcel.readString() as String,
        parcel.readParcelable(Bitmap::class.java.classLoader),
        parcel.readByte() != 0.toByte(),
        parcel.createStringArray() as Array<String>,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(tag)
        parcel.writeParcelable(bmpIcon, flags)
        parcel.writeByte(if (enabled) 1 else 0)
        parcel.writeStringArray(options)
        parcel.writeInt(select)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SpinnerItem) return false

        if (title != other.title) return false
        if (bmpIcon != other.bmpIcon) return false
        if (enabled != other.enabled) return false
        if (!options.contentEquals(other.options)) return false
        if (select != other.select) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + (bmpIcon?.hashCode() ?: 0)
        result = 31 * result + enabled.hashCode()
        result = 31 * result + options.contentHashCode()
        result = 31 * result + select
        return result
    }

    companion object CREATOR : Parcelable.Creator<SpinnerItem> {
        override fun createFromParcel(parcel: Parcel): SpinnerItem {
            return SpinnerItem(parcel)
        }

        override fun newArray(size: Int): Array<SpinnerItem?> {
            return arrayOfNulls(size)
        }
    }

    /**
     * 選択した操作があるか<br>
     * 初期値はfalse固定
     *
     * @return true: あり、false: なし
     */
    var focusable : Boolean = false

    /**
     * @since v1.2.0
     * アイテムの状態が変更されたときの処理
     * SettingItemsAdapterで使用する
     * 定義はActivity/Fragmentで行う
     */
    interface OnItemStateChangeListener {
        /**
         * スピナーの選択肢が変更されたときの追加処理
         *
         * @param adapter [SettingItemsAdapter]
         * @param position セレクタの選択肢ポジション
         */
        fun onItemSelectChanged(
            adapter: SettingItemsAdapter,
            position: Int
        )
    }
    var selectChangeListener: OnItemStateChangeListener? = null
}