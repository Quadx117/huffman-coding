/* -------------------------------------------------------------------------
   | Auteur(s) :   Eric Perron        IFT2810                              |
   |-----------------------------------------------------------------------|
   | Description :                                                         |
   |  - Implémentation d'une liste simplement chaînée (linked list)        |
   |-----------------------------------------------------------------------|
   | Dernière mise à jour : 18 octobre 2014                                |
   |-----------------------------------------------------------------------|
 */

package question2.collection;

/*
 * Classe implémentant une liste doublement chaînée à partir de la classe Node.
 * Nous utilisons des sentinnelles à chaque bout de la liste afin de rendre plus
 * rapide l'accès aux derniers éléments
 */
public class LinkedList<E> implements List<E> {

    protected Node head;  // Pointeur sur la tête de la liste
    protected Node tail;  // Pointeur sur la fin de la liste
    protected int size;   // Compteur du nombre d'élément (permet de facilement obtenir une
                          // méthode size() en O(1)).

    /**
     * Initialise un nouvel objet de type LinkedList.
     */
    public LinkedList() {
        head = new Node();
        tail = new Node();
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    /**
     * Retourne le nombre d'élément dans cette liste.
     * 
     * @return Le nombre d'élément dans cette liste.
     */
    public int size() {
        return size;
    }

    /**
     * Retourne {@code true} si cette liste est vide.
     * 
     * @return {@code true} si cette liste est vide.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Retourne l'élément à la position spécifiée dans cette liste.
     * 
     * @param index
     *            l'index de l'élément à retourner.
     * 
     * @return l'élément à la position spécifiée dans cette liste.
     */
    public E get(int index) {
        checkIndex(index, size);
        Node aRetourner;

        if (index > size / 2) {
            aRetourner = tail;
            for (; index < size; index++) {
                aRetourner = aRetourner.getPrev();
            }
        } else {
            aRetourner = head.getNext();
            for (; index > 0; index--) {
                aRetourner = aRetourner.getNext();
            }
        }

        return (E) aRetourner.getItem();
    }

    /**
     * Remplace l'élément à la position spécifiée dans cette liste
     * par l'élément spécifié.
     * 
     * @param index
     *            l'index de l'élément à remplacer.
     * 
     * @param e
     *            l'élément à enregistré à la position spécifiée.
     * 
     * @return l'ancien élément à la position spécifiée dans cette liste.
     */
    public E set(int index, E e) throws IndexOutOfBoundsException {
        checkIndex(index, size);
        Node aRetourner, aInserer = nodeWrapper(e);

        if (index > size / 2) {
            aRetourner = tail;
            for (; index < size; index++) {
                aRetourner = aRetourner.getPrev();
            }
        } else {
            aRetourner = head.getNext();
            for (; index > 0; index--) {
                aRetourner = aRetourner.getNext();
            }
        }

        aRetourner.getPrev().setNext(aInserer);
        aRetourner.getNext().setPrev(aInserer);
        aInserer.setPrev(aRetourner.getPrev());
        aInserer.setNext(aRetourner.getNext());
        aRetourner.setNext(null);
        aRetourner.setPrev(null);

        return (E) aRetourner.getItem();
    }

    /**
     * Ajoute l'élément à la fin de cette liste.
     * 
     * @param e
     *            L'élément à ajouter à cette liste.
     */
    public int add(E e) throws IndexOutOfBoundsException {
        Node n = nodeWrapper(e);
        return add(size, n);
    }

    /**
     * Insère l'élément dans cette liste à la position spécifiée.
     * 
     * @param index
     *            index à laquelle l'élément doit être inséré.
     * @param e
     *            L'élément à ajouter à cette liste.
     * 
     * @return
     *         l'index de l'élément s'il existe, -1 sinon.
     */
    public int add(int index, E e) throws IndexOutOfBoundsException {
        checkIndex(index, size + 1);
        Node n = nodeWrapper(e);
        return add(index, n);

    }

    // Méthode interne à la classe qui effectue l'ajout de l'élément à la list sous forme de Node.
    private int add(int i, Node n) {
        checkIndex(i, size);
        Node before = head, after = head.getNext();
        if (i > size / 2) {
            before = tail.getPrev();
            after = tail;
            for (int j = size; j > i; j--) {
                after = before;
                before = before.getPrev();
            }
        } else {
            for (int j = 0; j < i; j++) {
                before = after;
                after = after.getNext();
            }
        }

        before.setNext(n);
        n.setPrev(before);
        n.setNext(after);
        after.setPrev(n);
        size++;
        return i;
    }

    /**
     * Supprime l'élément de la liste à la position spécifiée.
     * 
     * @param index
     *            index de l'élément à supprimé.
     * 
     * @return l'élément qui a été supprimé.
     */
    public E remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index, size);
        Node before = head, aSupprimer = head.getNext(), after = head.getNext().getNext();

        if (index > size / 2) {
            after = tail;
            aSupprimer = after.getPrev();
            before = aSupprimer.getPrev();
            for (int j = size - 1; j > index; j--) {
                after = aSupprimer;
                aSupprimer = before;
                before = before.getPrev();
            }
        } else {
            for (int j = 0; j < index; j++) {
                before = aSupprimer;
                aSupprimer = after;
                after = after.getNext();
            }
        }

        before.setNext(after);
        after.setPrev(before);
        aSupprimer.setNext(null);
        aSupprimer.setPrev(null);
        size--;
        return (E) aSupprimer.getItem();
    }

    // Overloading de la méthode toString pour faciliter l'impression à l'écran de la structure.
    /*
     * Retourne une représentation en chaîne de caractère ({@code String}) de cette liste.
     * 
     * @return La représentation en chaîne de caractère de cet objet.
     * 
     * @see NodeHuffman#toString() {@code NodeHuffman.toString()}
     */
    public String toString() {
        String fullList = "<- ";
        if (size > 0) {
            Node x = head.getNext();
            fullList += x.toString();
            while (x != tail.getPrev()) {
                x = x.getNext();
                fullList += x.toString();
            }
        }
        return fullList + "->";
    }

    /* Helper method pour vérifier que l'index est valide */
    protected void checkIndex(int index, int n) throws IndexOutOfBoundsException {
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException("Illegal index: " + index);
        }
    }

    /* Helper method to wrap the object into a node */
    protected Node nodeWrapper(E e) {
        Node node;
        if (e instanceof Node) {
            node = (Node) e;
        } else {
            node = new Node(e);
        }
        return node;
    }
}
