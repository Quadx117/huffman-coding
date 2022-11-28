package question2.collection;

public interface List<E> {

    /**
     * Retourne le nombre d'élément dans cette liste.
     * 
     * @return Le nombre d'élément dans cette liste.
     */
    int size();

    /**
     * Retourne {@code true} si cette liste est vide.
     * 
     * @return {@code true} si cette liste est vide.
     */
    boolean isEmpty();

    /**
     * Retourne l'élément à la position spécifiée dans cette liste.
     * 
     * @param index
     *            l'index de l'élément à retourner.
     * 
     * @return l'élément à la position spécifiée dans cette liste.
     */
    E get(int index) throws IndexOutOfBoundsException;

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
    E set(int index, E e) throws IndexOutOfBoundsException;

    /**
     * Ajoute l'élément à la fin de cette liste.
     * 
     * @param index
     *            l'index à laquelle ajouter l'élément.
     * @param e
     *            L'élément à ajouter à cette liste.
     */
    int add(int index, E e) throws IndexOutOfBoundsException;

    /**
     * Supprime l'élément de la liste à la position spécifiée.
     * 
     * @param index
     *            index de l'élément à supprimé.
     * 
     * @return l'élément qui a été supprimé.
     */
    E remove(int index) throws IndexOutOfBoundsException;
}
