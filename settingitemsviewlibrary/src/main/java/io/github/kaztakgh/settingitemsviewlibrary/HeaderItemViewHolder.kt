/**
 * @file HeaderItemViewHolder.kt
 */
package io.github.kaztakgh.settingitemsviewlibrary

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * ヘッダーのビューの表示に使用する
 *
 * @constructor
 * ヘッダーを使用するアイテムのビューを呼び出す
 *
 * @param view viewのレイアウトリソース
 */
internal class HeaderItemViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    private val headerView: TextView = view.findViewById(R.id.tv_header)
    /**
     * ヘッダーのテキスト
     */
    var headerText: String
        get() {
            return headerView.text.toString()
        }
        set(value) {
            headerView.text = value
        }

    /**
     * テキストの色
     */
    var textColor: Int
        get() {
            return headerView.currentTextColor
        }
        set(value) {
            headerView.setTextColor(value)
        }
}