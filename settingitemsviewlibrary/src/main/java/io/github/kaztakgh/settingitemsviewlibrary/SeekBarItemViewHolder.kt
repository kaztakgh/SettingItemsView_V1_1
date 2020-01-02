/**
 * @file SeekBarItemViewHolder.kt
 */
package io.github.kaztakgh.settingitemsviewlibrary

import android.view.View
import android.widget.SeekBar

/**
 * 状態を数値バーで指定するアイテムのビューの表示に使用する
 *
 * @constructor
 * シークバーを使用するアイテムのレイアウトを呼び出す
 *
 * @param view viewのレイアウトリソース
 */
internal class SeekBarItemViewHolder(
    view: View
) : NormalItemViewHolder(view) {
    private val stateBar: SeekBar = view.findViewById(R.id.sb_state)

    /**
     * 選択可能であるか
     */
    override var isSelectable: Boolean = true
        get() = super.isSelectable
        set(value) {
            field = value
            // 選択可能であるかどうかを透明度で示す
            // 選択可能な場合は全てのパーツを不透明、選択不可能な場合は全てのパーツを半透明に変更する
            val alpha: Float = if (value) 1.0f else 0.5f
            titleView.alpha = alpha
            textView.alpha = alpha
            if (iconView.visibility == View.VISIBLE)
                iconView.alpha = alpha

            // 選択不可能の場合はシークバーの操作を行えないようにする
            stateBar.isEnabled = value
        }

    /**
     * バーのプログレス
     * 初期値の設定が必要
     */
    var state : Int
        get() = stateBar.progress
        set(value) {
            stateBar.progress = value
        }

    /**
     * バーの最大値
     * 初期値の設定が必要
     */
    var max : Int
        get() = stateBar.max
        set(value) {
            stateBar.max = value
        }

    /**
     * 数値バーの取得
     *
     * @return 表示している数値バー
     */
    fun getStateBar() : SeekBar = stateBar
}