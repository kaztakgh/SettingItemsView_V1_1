/**
 * @file SeekBarItem.kt
 */
package io.github.kaztakgh.settingitemsviewlibrary

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable

/**
 * SeekBarで値を指定するアイテムの作成
 *
 * @property title タイトル(省略不可)
 * @property tag タグ(識別子、省略不可)
 * @property bmpIcon 左側に表示するアイコン(省略時null)
 * @property enabled 選択可能であるか(省略時true)
 * @property state バーの初期値(省略時0)
 * @property max バーの最大値(省略時100)
 * @property min バーの最小値(省略時0)
 * @property div バーの分割値(省略時1)
 * @property unit 単位(省略時無し)
 * @property paramsArray 値の配列<br>このプロパティを指定した場合、バーの値の指定方法が配列に準拠する
 */
data class SeekBarItem(
    override val title: String,
    override val tag: String,
    val bmpIcon: Bitmap? = null,
    var enabled: Boolean = true,
    var state: Int = 0,
    val max: Int = 100,
    val min: Int = 0,
    val div: Int = 1,
    val unit: String = "",
    val paramsArray: IntArray? = null
) : ItemInterface, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() as String,
        parcel.readString() as String,
        parcel.readParcelable(Bitmap::class.java.classLoader),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() as String,
        parcel.createIntArray()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(tag)
        parcel.writeParcelable(bmpIcon, flags)
        parcel.writeByte(if (enabled) 1 else 0)
        parcel.writeInt(state)
        parcel.writeInt(max)
        parcel.writeInt(min)
        parcel.writeInt(div)
        parcel.writeString(unit)
        parcel.writeIntArray(paramsArray)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SeekBarItem) return false

        if (title != other.title) return false
        if (tag != other.tag) return false
        if (bmpIcon != other.bmpIcon) return false
        if (enabled != other.enabled) return false
        if (state != other.state) return false
        if (max != other.max) return false
        if (min != other.min) return false
        if (div != other.div) return false
        if (unit != other.unit) return false
        if (paramsArray != null) {
            if (other.paramsArray == null) return false
            if (!paramsArray.contentEquals(other.paramsArray)) return false
        } else if (other.paramsArray != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + tag.hashCode()
        result = 31 * result + (bmpIcon?.hashCode() ?: 0)
        result = 31 * result + enabled.hashCode()
        result = 31 * result + state
        result = 31 * result + max
        result = 31 * result + min
        result = 31 * result + div
        result = 31 * result + unit.hashCode()
        result = 31 * result + (paramsArray?.contentHashCode() ?: 0)
        return result
    }

    companion object CREATOR : Parcelable.Creator<SeekBarItem> {
        override fun createFromParcel(parcel: Parcel): SeekBarItem {
            return SeekBarItem(parcel)
        }

        override fun newArray(size: Int): Array<SeekBarItem?> {
            return arrayOfNulls(size)
        }
    }

    /**
     * max, min, divを用いて等間隔の配列を作成
     * paramsArrayを設定した場合はparamsArrayをそのまま返す
     *
     * @return 数値の配列
     */
    internal fun createParamsArray() : IntArray {
        if (paramsArray != null) return paramsArray
        val range: Int = max - min
        val size: Int = range / div + 1
        return IntArray(size) {it * div + min}
    }

    /**
     * @since v1.2.0
     * アイテムの状態が変更されたときの処理
     * SettingItemsAdapterで使用する
     * 定義はActivity/Fragmentで行う
     */
    interface OnItemStateChangeListener {
        /**
         * シークバーの値を変更した後の処理
         *
         * @param adapter [SettingItemsAdapter]
         * @param progress シークバーのプログレス
         */
        fun onItemValueChange(
            adapter: SettingItemsAdapter,
            progress: Int
        )
    }
    var valueChangeListener: OnItemStateChangeListener? = null
}