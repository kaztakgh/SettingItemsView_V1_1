[settingitemsviewlibrary](../../index.md) / [io.github.kaztakgh.settingitemsviewlibrary](../index.md) / [SettingItemsAdapter](index.md) / [onCreateViewHolder](./on-create-view-holder.md)

# onCreateViewHolder

`fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): ViewHolder`

Called when RecyclerView needs a new [ViewHolder](#) of the given type to represent
an item.

This new ViewHolder should be constructed with a new View that can represent the items
of the given type. You can either create a new View manually or inflate it from an XML
layout file.

The new ViewHolder will be used to display items of the adapter using
[.onBindViewHolder](#). Since it will be re-used to display
different items in the data set, it is a good idea to cache references to sub views of
the View to avoid unnecessary [View.findViewById](https://developer.android.com/reference/android/view/View.html#findViewById(int)) calls.

### Parameters

`parent` - The ViewGroup into which the new View will be added after it is bound to
an adapter position.

`viewType` - The view type of the new View.

**Return**
A new ViewHolder that holds a View of the given view type.

