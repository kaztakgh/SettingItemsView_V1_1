[settingitemsviewlibrary](../../index.md) / [io.github.kaztakgh.settingitemsviewlibrary](../index.md) / [HeaderItem](index.md) / [describeContents](./describe-contents.md)

# describeContents

`fun describeContents(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Describe the kinds of special objects contained in this Parcelable
instance's marshaled representation. For example, if the object will
include a file descriptor in the output of [.writeToParcel](#),
the return value of this method must include the
[.CONTENTS_FILE_DESCRIPTOR](#) bit.

**Return**
a bitmask indicating the set of special object types marshaled
by this Parcelable object instance.

