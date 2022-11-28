/* -------------------------------------------------------------------------
   | Auteur(s) :   Jonathan Rioux                                          |
   |-----------------------------------------------------------------------|
   | Description :                                                         |
   |  - Cette classe est fournit par le démonstrateur.                     |
   |-----------------------------------------------------------------------|
   | Dernière mise à jour : n/a                                            |
   |-----------------------------------------------------------------------|
 */

package question2.collection;

public class Node {

    /*
     * Crée un "Node" qui peut être utilisé pour une liste chaînée.
     * 
     * Celui-ci est schématiquement représenté comme suit :
     * 
     *                +---+--------+---+
     *           <----| P |  Item  | N |---->
     *                +---+--------+---+
     * 
     * "Item" représente l'objet contenu dans le coeur du Node;
     * "P" (prev dans la classe) pointe sur le Node précédent;
     * "N" (next dans la classe) pointe sur l'objet suivant.
     */

    private Object item;
    private Node next;
    private Node prev;

    /**
     * Construis un objet de type {@code Node} ayant comme {@code item} {@code null}.
     * */
    public Node() {
        this(null);
    }

    /**
     * Construis un objet de type {@code Node} ayant comme {@code item} l'objet
     * spécifié.
     * */
    public Node(Object init) {
        item = init;
        next = null;
        prev = null;
    }

    // "Getters" et "Setters"
    /**
     * Remplace l'item de ce noeud par l'item spécifié.
     * 
     * @param newItem
     *            le nouvel item de ce noeud.
     * */
    public void setItem(Object newItem) {
        item = newItem;
    }

    /**
     * Remplace le {@code suivant} de ce noeud par le suivant spécifié.
     * 
     * @param newNext
     *            le nouveau noeud suivant de ce noeud.
     * */
    public void setNext(Node newNext) {
        next = newNext;
    }

    /**
     * Remplace le {@code précédent} de ce noeud par le suivant spécifié.
     * 
     * @param newPrev
     *            le nouveau noeud précédent de ce noeud.
     * */
    public void setPrev(Node newPrev) {
        prev = newPrev;
    }

    /**
     * Retourne l'item de ce noeud.
     * 
     * @return l'item de ce noeud.
     * */
    public Object getItem() {
        return item;
    }

    /**
     * Retourne le noeud suivant ce noeud.
     * 
     * @return le noeud suivant ce noeud.
     * */
    public Node getNext() {
        return next;
    }

    /**
     * Retourne le noeud précédent ce noeud.
     * 
     * @return le noeud précédent ce noeud.
     * */
    public Node getPrev() {
        return prev;
    }

    // Overloading de la méthode toString pour faciliter l'impression à l'écran de la structure.
    /**
     * Retourne une représentation en chaîne de caractère ({@code String}) de ce noeud sous la forme :
     * représentation en chaîne de caractère ({@code String}) de l'objet {@code item} de ce noeud.
     * Autrement dit on affiche : {@code item.toString()}
     * 
     * @return La représentation en chaîne de caractère ({@code String}) cet objet.
     * */
    @Override
    public String toString() {
        return String.format("[%s] ", item);
    }
}
