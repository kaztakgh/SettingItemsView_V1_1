/**
 * @file ItemInterface.kt
 */
package io.github.kaztakgh.settingitemsviewlibrary

/**
 * 設定アイテムの共通要素<br>
 *     省略不可能もしくは非推奨の要素のみ記載する<br>
 *         アイテムを作成するときはこのinterfaceを継承すること
 */
interface ItemInterface {
    /**
     * タイトル
     */
    val title: String
    /**
     * タグ
     */
    val tag: String
}