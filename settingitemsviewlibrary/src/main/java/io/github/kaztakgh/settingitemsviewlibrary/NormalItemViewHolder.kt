/**
 * @file NormalItemViewHolder.kt
 */
package io.github.kaztakgh.settingitemsviewlibrary

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.kaztakgh.viewhelper.ViewHelper

/**
 * SettingItemsViewのタイトル・テキスト・アイコンで表示されるアイテムの表示に使用する
 *
 * @constructor
 * 通常のアイテムのレイアウトを呼び出す
 *
 * @param view viewのレイアウトリソース
 */
open class NormalItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    /**
     * アイテムをクリックしたときのリスナー
     */
    interface ItemClickListener {
        /**
         * アイテムをクリックしたときの処理
         *
         * @param view レイアウトビュー
         * @param position アダプター内のアイテムの位置
         */
        fun onItemClick(view: View, position: Int)
    }
    open val titleView: TextView = view.findViewById(R.id.tv_title)
    open val textView: TextView = view.findViewById(R.id.tv_text)
    open val iconView: ImageView = view.findViewById(R.id.iv_icon)

    /**
     * タイトル
     */
    var title : String
        get() = titleView.text.toString()
        set(value) {
            titleView.text = value
        }

    /**
     * テキスト
     */
    open var text : String
        get() = textView.text.toString()
        set(value) {
            textView.text = value
        }

    /**
     * アイコン
     */
    var bmpIcon: Bitmap?
        get() {
            // セットされている場合のみ画像(最大48dp)を返す
            return if (iconView.visibility == View.VISIBLE)
                ViewHelper.drawableToBitmap(iconView.drawable)
            else
                null
        }
        set(value) {
            if (value !== null) {
                iconView.setImageBitmap(value)
            }
            // nullをセットした場合、アイコンのビューを消去する
            else {
                iconView.visibility = View.GONE
            }
        }

    /**
     * 選択可能であるか
     */
    open var isSelectable: Boolean = true
        set(value) {
            field = value
            // 選択可能であるかどうかを透明度で示す
            // 選択可能な場合は全てのパーツを不透明、選択不可能な場合は全てのパーツを半透明に変更する
            val alpha: Float = if (value) 1.0f else 0.5f
            titleView.alpha = alpha
            textView.alpha = alpha
            if (iconView.visibility == View.VISIBLE)
                iconView.alpha = alpha
        }

    /**
     * アイテムをクリックしたときのリスナー
     */
    open var itemClickListener: ItemClickListener? = null
}