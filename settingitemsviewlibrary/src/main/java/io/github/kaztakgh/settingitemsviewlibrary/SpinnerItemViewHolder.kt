/**
 * @file SpinnerItemViewHolder.kt
 */
package io.github.kaztakgh.settingitemsviewlibrary

import android.view.View
import android.widget.Spinner

/**
 * 状態をスピナーで指定するアイテムのビューの表示に使用する
 *
 * @constructor
 * スピナーを使用するアイテムのレイアウトを呼び出す
 *
 * @param view viewのレイアウトリソース
 */
internal class SpinnerItemViewHolder(
    view: View
) : NormalItemViewHolder(view) {
    private val spinner: Spinner = view.findViewById(R.id.spn_options)

    /**
     * テキスト
     */
    override var text: String = ""
        get() = super.text
        set(value) {
            // テキストが設定された場合、空文字に変更
            if (value.isNotEmpty()) {
                field = ""
            }
        }

    /**
     * 選択可能であるか
     */
    override var isSelectable: Boolean
        get() {
            return this.spinner.isEnabled
        }
        set(value) {
            // 選択状態
            val alpha: Float = if (value) 1.0f else 0.5f
            titleView.alpha = alpha
            textView.alpha = alpha
            spinner.alpha = alpha
            spinner.isEnabled = value
            spinner.isClickable = value
        }

    /**
     * スピナー要素の取得
     * 他の要素に影響を与える機能を持つため、clickListenerをAdapterで記述する
     *
     * @return Spinner
     */
    fun getOptionsSpinner(): Spinner = spinner
}