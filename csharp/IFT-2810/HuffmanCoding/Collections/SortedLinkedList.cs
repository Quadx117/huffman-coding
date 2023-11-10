namespace HuffmanCoding.Collections;

using System.Collections.Generic;

/// <summary>
/// Class implementing a doubly linked list sorted at any time. This list does not
/// allow duplicates. The object to be added to the list must implement the <c>IComparable</c>
/// interface. The list is sorted in descending order.
/// </summary>
/// <typeparam name="E"></typeparam>
internal class SortedLinkedList<E> : LinkedList<E> where E : IComparable<E>
{
    /// <summary>
    /// Initializes a new instance of the <see cref="SortedLinkedList{E}"/>
    /// class that is empty.
    /// </summary>
    public SortedLinkedList() : base() { }

    /// <summary>
    /// Adds the element to this list if it does not exist. If it does exist, only
    /// the element index is returned. The element must implement the
    /// <see cref="IComparable{E}"/> interface.
    /// </summary>
    /// <param name="e">The element to add to the list.</param>
    /// <returns>The index of the element if it alread exists in the list, -1 otherwise.</returns>
    public int Add(E e)
    {
        LinkedListNode<E> n = new(e);

        if (Count == 0)
        {
            AddFirst(n);
            return -1;
        }
        else
        {
            // NOTE(PERE): First and Last are not sentinels like my original
            // Java implementation.
            LinkedListNode<E>? current = First;
            LinkedListNode<E>? after = null;
            int index = -1;

            while (current != null)
            {
                if (e.Equals(current.Value))
                {
                    return index;
                }
                else if (e.CompareTo(current.Value) < 0)
                {
                    after = current;
                }

                current = current.Next;
                index++;
            }

            if (after == null)
            {
                // We never found a node that was bigger than new element,
                // so we add it at the start of the collection.
                AddFirst(n);
            }
            else
            {
                AddAfter(after, n);
            }

            return -1;
        }
    }
}
