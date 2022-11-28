/* -------------------------------------------------------------------------
   | Auteur(s) :   Eric Perron        IFT2810                              |
   |-----------------------------------------------------------------------|
   | Description :                                                         |
   |  - Implémentation d'une liste simplement chaînée trié                 |
   |-----------------------------------------------------------------------|
   | Dernière mise à jour : 18 octobre 2014                                |
   |-----------------------------------------------------------------------|
 */

package question2.collection;

/*
 * Classe implémentant une liste doublement chaînée trié en tout temps à partir de la classe
 * LinkedList.  Cette liste ne permet pas l'ajout de doubons.  L'objet à ajouter dans la liste
 * doit implémenter l'interface Comparable.  La liste est trié de façon décroissante.
 */
public class SortedLinkedList<E extends Comparable<E>> extends LinkedList<E> {

    /**
     * Initialise un nouvel objet de type SortedLinkedList.
     */
    public SortedLinkedList() {
        super();
    }

    /**
     * Ajoute l'élément à cette liste si cet élément n'existe pas.
     * S'il existe, on retourne seulement l'index de l'élément.
     * L'élément doit implémenter l'interface Comparable.
     * 
     * @param e
     *            L'élément à ajouter à cette liste.
     * 
     * @return
     *         l'index de l'élément s'il existe, -1 sinon.
     */
    @Override
    public int add(E e) throws IndexOutOfBoundsException {

        Node current, before, after = head.getNext();
        current = before = head;
        int index = -1;

        while (current != tail) {
            if (current != null && e.equals(current.getItem())) {
                return index;
            } else if (after != tail && e.compareTo((E) after.getItem()) < 0) {
                before = before.getNext();
                after = after.getNext();
            }

            current = current.getNext();
            index++;
        }

        Node n = nodeWrapper(e);
        before.setNext(n);
        n.setPrev(before);
        n.setNext(after);
        after.setPrev(n);
        size++;
        return -1;
    }
}
