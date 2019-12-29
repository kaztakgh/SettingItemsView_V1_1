/**
 * @file DateItem.kt
 */
package io.github.kaztakgh.settingitemsviewlibrary

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import io.github.kaztakgh.dialogfragmentslibrary.DateTimeDialog
import java.util.*

/**
 * 日付を選択するアイテムの作成
 *
 * @property title タイトル(省略不可)
 * @property tag タグ(識別子、省略不可)
 * @property date 日付
 * @property bmpIcon 左側に表示するアイコン(省略時null)
 * @property enabled 選択可能であるか(省略時true)
 * @property format 出力フォーマット
 * @property requestCode リクエストコード
 */
data class DateItem(
    override val title: String,
    override val tag: String,
    var date: Calendar = Calendar.getInstance(),
    val bmpIcon: Bitmap? = null,
    var enabled: Boolean = true,
    val format: String = "yyyy/MM/dd",
    val requestCode: Int = 1
) : ItemInterface, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() as String,
        parcel.readString() as String,
        parcel.readSerializable() as Calendar,
        parcel.readParcelable(Bitmap::class.java.classLoader),
        parcel.readByte() != 0.toByte(),
        parcel.readString() as String,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(tag)
        parcel.writeSerializable(date)
        parcel.writeParcelable(bmpIcon, flags)
        parcel.writeByte(if (enabled) 1 else 0)
        parcel.writeString(format)
        parcel.writeInt(requestCode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DateItem> {
        override fun createFromParcel(parcel: Parcel): DateItem {
            return DateItem(parcel)
        }

        override fun newArray(size: Int): Array<DateItem?> {
            return arrayOfNulls(size)
        }
    }

    /**
     * 日付の出力
     */
    internal val text: String
        get() {
            return android.text.format.DateFormat.format(format, date) as String
        }

    /**
     * ダイアログの構築
     *
     * @return 日付選択ダイアログ
     */
    internal fun buildDialog(): DateTimeDialog {
        return DateTimeDialog.Builder()
            .date(date)
            .tag(tag)
            .requestCode(requestCode)
            .build()
    }
}