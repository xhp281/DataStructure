package com.xhp281.map;

/**
 * User: FenDou
 * Date: 2019-06-04 15:36
 * Description:
 */

public interface Map<K,V> {

    /* 尺寸 */
    int size();

    /* 是不是空 */
    boolean isEmpty();

    /* 清空 */
    void clear();

    /* 添加 */
    V put(K key, V value);

    /* 获取 */
    V get(K key);

    /* 删除 */
    V remove(K key);

    /* 是不是包含Key */
    boolean containsKey(K key);

    /* 是不是包含value */
    boolean containsValue(V value);

    /* 遍历 */
    void traversal(Visitor<K, V> visitor);

    public static abstract class Visitor<K, V> {
        boolean stop;
        public abstract boolean visit(K key, V value);
    }
}
