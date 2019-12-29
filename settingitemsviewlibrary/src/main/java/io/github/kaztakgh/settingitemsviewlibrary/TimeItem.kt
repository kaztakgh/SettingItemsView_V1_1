/**
 * @file TimeItem.kt
 */
package io.github.kaztakgh.settingitemsviewlibrary

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import io.github.kaztakgh.dialogfragmentslibrary.DateTimeDialog
import java.util.*

/**
 * 時刻を選択するアイテムの作成
 *
 * @property title タイトル(省略不可)
 * @property tag タグ(識別子、省略不可)
 * @property time 時刻
 * @property bmpIcon 左側に表示するアイコン(省略時null)
 * @property enabled 選択可能であるか(省略時true)
 * @property display24h 24時間表記(省略時false)
 * @property format24h 24時間表記の場合のフォーマット
 * @property format12h 12時間表記の場合のフォーマット
 * @property requestCode リクエストコード
 */
data class TimeItem(
    override val title: String,
    override val tag: String,
    var time: Calendar = Calendar.getInstance(),
    val bmpIcon: Bitmap? = null,
    var enabled: Boolean = true,
    val display24h: Boolean = false,
    val format24h: String = "H:mm",
    val format12h: String = "h:mm a",
    val requestCode: Int = 1
) : ItemInterface, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() as String,
        parcel.readString() as String,
        parcel.readSerializable() as Calendar,
        parcel.readParcelable(Bitmap::class.java.classLoader),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString() as String,
        parcel.readString() as String,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(tag)
        parcel.writeSerializable(time)
        parcel.writeParcelable(bmpIcon, flags)
        parcel.writeByte(if (enabled) 1 else 0)
        parcel.writeByte(if (display24h) 1 else 0)
        parcel.writeString(format24h)
        parcel.writeString(format12h)
        parcel.writeInt(requestCode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TimeItem> {
        override fun createFromParcel(parcel: Parcel): TimeItem {
            return TimeItem(parcel)
        }

        override fun newArray(size: Int): Array<TimeItem?> {
            return arrayOfNulls(size)
        }
    }

    /**
     * 時刻の出力
     */
    internal val text: String
        get() {
            val format = if (display24h) format24h else format12h
            return android.text.format.DateFormat.format(format, time) as String
        }

    /**
     * ダイアログの構築
     *
     * @return 時刻選択ダイアログ
     */
    internal fun buildDialog(): DateTimeDialog {
        return DateTimeDialog.Builder()
            .time(time)
            .tag(tag)
            .display24h(display24h)
            .requestCode(requestCode)
            .build()
    }
}