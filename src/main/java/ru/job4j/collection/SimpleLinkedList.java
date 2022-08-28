package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private int size;
    private int modCount;
    private Node<E> first;
    private Node<E> last;

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> currentNode = first;
        int i = 0;
        while (i < index) {
            currentNode = currentNode.next;
            i++;
        }
        return currentNode.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final int expectedModCount = modCount;
            private Node<E> point = first;
            private int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> prevEl = point;
                point = point.next;
                return prevEl.item;
            }
        };
    }
}