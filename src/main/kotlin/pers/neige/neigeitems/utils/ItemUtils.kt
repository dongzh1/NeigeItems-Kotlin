package pers.neige.neigeitems.utils

import taboolib.module.nms.ItemTag
import taboolib.module.nms.ItemTagData
import taboolib.module.nms.ItemTagData.translateList
import taboolib.module.nms.ItemTagList
import taboolib.module.nms.ItemTagType


object ItemUtils {
    // HashMap 转 ItemTag
    @JvmStatic
    fun HashMap<*, *>.toItemTag(): ItemTag {
        val itemTag = ItemTag()
        for ((key, value) in this) {
            itemTag[key as String] = value.toItemTagData()
        }
        return itemTag
    }

    // 类型强制转换
    @JvmStatic
    fun String.cast(): Any {
        return when {
            this.startsWith("(Byte) ") -> this.substring(7, this.length).toByte()
            this.startsWith("(Short) ") -> this.substring(8, this.length).toShort()
            this.startsWith("(Int) ") -> this.substring(6, this.length).toInt()
            this.startsWith("(Long) ") -> this.substring(7, this.length).toLong()
            this.startsWith("(Float) ") -> this.substring(8, this.length).toFloat()
            this.startsWith("(Double) ") -> this.substring(9, this.length).toDouble()
            else -> this
        }
    }

    // 类型强制转换
    @JvmStatic
    fun Any.cast(): Any {
        return when (this) {
            is String -> this.cast()
            else -> this
        }
    }

    // 类型强制转换
    @JvmStatic
    fun String.castToItemTagData(): ItemTagData {
        return when {
            this.startsWith("(Byte) ") -> ItemTagData(this.substring(7, this.length).toByte())
            this.startsWith("(Short) ") -> ItemTagData(this.substring(8, this.length).toShort())
            this.startsWith("(Int) ") -> ItemTagData(this.substring(6, this.length).toInt())
            this.startsWith("(Long) ") -> ItemTagData(this.substring(7, this.length).toLong())
            this.startsWith("(Float) ") -> ItemTagData(this.substring(8, this.length).toFloat())
            this.startsWith("(Double) ") -> ItemTagData(this.substring(9, this.length).toDouble())
            else -> ItemTagData(this)
        }
    }

    // 转 ItemTagData
    @JvmStatic
    fun Any.toItemTagData(): ItemTagData {
        return when (this) {
            is ItemTagData -> this
            is ItemTagList -> this
            is ItemTag -> this
            is Byte -> ItemTagData(this)
            is Short -> ItemTagData(this)
            is Int -> ItemTagData(this)
            is Long -> ItemTagData(this)
            is Float -> ItemTagData(this)
            is Double -> ItemTagData(this)
            is ByteArray -> ItemTagData(this)
            is IntArray -> ItemTagData(this)
            is String -> this.castToItemTagData()
            is List<*> -> {
                val list = this.map { it?.cast() ?: it }
                when {
                    list.all { it is Byte } -> ByteArray(list.size){ list[it] as Byte }.toItemTagData()
                    list.all { it is Int } -> IntArray(list.size){ list[it] as Int }.toItemTagData()
                    else -> {
                        val itemTagList = ItemTagList()
                        for (obj in list) {
                            obj?.toItemTagData()?.let { itemTagList.add(it) }
                        }
                        itemTagList
                    }
                }
            }
            is HashMap<*, *> -> ItemTagData(this.toItemTag())
            else -> ItemTagData("nm的你塞了个什么东西进来，我给你一拖鞋")
        }
    }

    // ItemTag 合并(后者覆盖前者)
    @JvmStatic
    fun ItemTag.coverWith(itemTag: ItemTag): ItemTag {
        // 遍历附加NBT
        for ((key, value) in itemTag) {
            // 如果二者包含相同键
            val overrideValue = this[key]
            if (overrideValue != null) {
                // 如果二者均为COMPOUND
                if (overrideValue.type == ItemTagType.COMPOUND
                    && value.type == ItemTagType.COMPOUND) {
                    // 合并
                    this[key] = overrideValue.asCompound().coverWith(value.asCompound())
                } else {
                    // 覆盖
                    this[key] = value
                }
            } else {
                // 添加
                this[key] = value
            }
        }
        return this
    }
}