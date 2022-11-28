/* -------------------------------------------------------------------------
   | Auteur(s) :   Eric Perron        IFT2810                              |
   |-----------------------------------------------------------------------|
   | Dernière mise à jour : 18 novembre 2014                               |
   |-----------------------------------------------------------------------|
 */
package question2.collection;

/**
 * Classe permettant de crée un noeud pour construire l'arbre de recherche
 * pour l'encodage de Huffman.
 * */
public class NodeHuffman implements Comparable<NodeHuffman> {

    /**
     * Le caractère que ce noeud contient.
     * */
    private char character;

    /**
     * La fréquence d'apparition du caractère que ce noeud contient.
     * */
    private int freq;

    /**
     * Les noeuds enfants (fils gauche et fils droit) de ce noeud.
     * */
    private NodeHuffman left, right;

    /**
     * Constructeur
     * Ce constructeur appel le constructeur complètement paramétré pour créé un noeud dont les enfant sont
     * null (pour créé une feuille par exemple).
     * 
     * @param character
     *            le caractère de ce noeud
     * @param freq
     *            la fréquence du caractère
     * */
    public NodeHuffman(char character, int freq) {
        this(character, freq, null, null);
    }

    /**
     * Constructeur complètement paramétré.
     * 
     * @param character
     *            le caractère de ce noeud
     * @param freq
     *            la fréquence du caractère
     * @param left
     *            le fils gauche de ce noeud
     * @param right
     *            le fils droit de ce noeud
     * */
    public NodeHuffman(char character, int freq, NodeHuffman left, NodeHuffman right) {
        this.character = character;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    /**
     * Retourne {@code true} si ce noeud est une feuille
     * 
     * @return {@code true} si ce noeud est une feuille.
     * */
    public boolean isLeaf() {
        return (left == null && right == null);
    }

    // "Getters" et "Setters"
    /**
     * Remplace le caractère de ce noeud par le caractère spécifié.
     * 
     * @param newChar
     *            le nouveau caractère de ce noeud.
     * */
    public void setChar(char newChar) {
        this.character = newChar;
    }

    /**
     * Remplace la fréquence du caractère de ce noeud par la fréquence spécifiée.
     * 
     * @param newFreq
     *            la nouvelle fréquence du caractère de ce noeud.
     * */
    public void setFreq(int newFreq) {
        this.freq = newFreq;
    }

    /**
     * Remplace le fils gauche ({@code left}) de ce noeud par le fils gauche spécifié.
     * 
     * @param newLeft
     *            le nouveau noeud fils gauche de ce noeud.
     * */
    public void setLeft(NodeHuffman newLeft) {
        this.left = newLeft;
    }

    /**
     * Remplace le fils droit ({@code left}) de ce noeud par le fils droit spécifié.
     * 
     * @param newRight
     *            le nouveau noeud fils droit de ce noeud.
     * */
    public void setRight(NodeHuffman newRight) {
        this.right = newRight;
    }

    /**
     * Retourne le caractère de ce noeud.
     * 
     * @return le caractère de ce noeud.
     * */
    public char getChar() {
        return this.character;
    }

    /**
     * Retourne la fréquence du caractère de ce noeud.
     * 
     * @return la fréquence du caractère de ce noeud.
     * */
    public int getFreq() {
        return this.freq;
    }

    /**
     * Retourne le noeud fils gauche ce noeud.
     * 
     * @return le noeud fils gauche ce noeud.
     * */
    public NodeHuffman getLeft() {
        return this.left;
    }

    /**
     * Retourne le noeud fils droit ce noeud.
     * 
     * @return le noeud fils droit ce noeud.
     * */
    public NodeHuffman getRight() {
        return this.right;
    }

    // Overloading de la méthode toString pour faciliter l'impression à l'écran de la structure.
    /*
     * Retourne une représentation en chaîne de caractère ({@code String}) de ce noeud sous la forme :
     * caractère suivi du signe égal ("<tt>=</tt>") suivi de la fréquence. Autrement dit, on affiche :
     * {@code character.toString() + "=" + freq.toString()}
     * 
     * @return La représentation en chaîne de caractère de cet objet.
     */
    public String toString() {
        return character + "=" + freq;
    }

    /**
     * Retourne le résultat de la comparaison entre deux objets de type {@code NodeHuffman}.
     * La comparaison est d'abords basé sur la fréquence. Si la fréquence est identique, alors
     * on compare la valeur du caractère.
     * 
     * @param autre
     *            l'autre élément avec lequel effectué la comparaison.
     * 
     * @return le résultat de la comparaison entre deux objets de type {@code NodeHuffman}.
     */
    public int compareTo(NodeHuffman autre) {
        int result = this.freq - autre.freq;           // La plus haute fréquence d'abords
        if (result == 0) {
            result = autre.character - this.character; // Le plus petit code ASCII en premier si fréquence égal
        }
        return result;
    }

    /**
     * Vérifie si un objet NodeHuffman est égal à un autre objet NodeHuffman.
     * Nous effectuons la comparaison sur le caractère seulement.
     * Ceci nous permet de savoir si nous avons des doublons.
     * 
     * @param other
     *            l'autre élément avec lequel effectué la comparaison.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof NodeHuffman)
            return this.character == (((NodeHuffman) other).character);
        else
            return false;
    }

    /*
     * Overloading de la méthode hashCode de la classe Objet afin de respecter
     * le contrat avec celle-ci. Pas utilisé pour le moment dans notre implémentation.
     */
    @Override
    public int hashCode() {
        return ((int) character);
    }
}
