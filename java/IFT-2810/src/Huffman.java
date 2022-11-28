/* -------------------------------------------------------------------------
   | Auteur(s) :   Eric Perron        IFT2810                              |
   |-----------------------------------------------------------------------|
   | Dernière mise à jour : 18 novembre 2014                               |
   |-----------------------------------------------------------------------|
 */

package question2;

import question2.collection.NodeHuffman;
import question2.collection.SortedLinkedList;

/**
 * Classe qui contient les méthodes {@code static} pour encoder et decoder selon
 * l'algorithme de Huffman. Cette classe est non instanciable, car elle ne contient
 * que des méthodes {@code static}.
 * */
public final class Huffman {

    /**
     * Valeur maximale de code ASCII. Cet encodeur de Huffman fonctionne pour
     * les alphabets contenants au maximum 256 caractères.
     */
    private static final int MAX_NUM_CODE = 256;

    /**
     * Constructeur privé pour empêcher l'instanciation de cette classe. Si on
     * réussis à instancié cette classe (ex : par la réflexion) on lance une
     * exception
     * 
     * @throws RuntimeException
     *             si on réussis à instancié cette classe.
     */
    private Huffman() throws RuntimeException {
        throw new RuntimeException();
    }

    /**
     * Cette méthode permet d'encoder les caractères d'une chaîne de caractères
     * ({@code String} spécifié et retourne une chaîne de caractère ({@code String} contenant la chaîne
     * encoder.
     * 
     * @param toEncode
     *            {@code String} à encoder.
     * 
     * @return la chaîne de caractère encoder.
     * */
    public static String encode(String toEncode) {
        // On dépose les chaînes de caractères représentant les caractères encodés dans un tableau
        String[] listeCodeHuffman = Huffman.buildHuffmanTree(toEncode);

        return encode(toEncode, listeCodeHuffman);
    }

    /**
     * Cette méthode permet d'encoder les caractères d'une chaîne de caractères
     * ({@code String} spécifié en fonction des codes spécifiés et retourne une
     * chaîne de caractère ({@code String}) contenant la chaîne encoder.
     * 
     * @param toEncode
     *            chaîne de caractère {@code String} à encoder.
     * @param huffmanCode
     *            tableau de chaîne de caractère {@code String} contenant les codes.
     * 
     * @return la chaîne de caractère encoder.
     * */
    private static String encode(String toEncode, String[] huffmanCode) {
        // Stringbuilder pour construire la chaîne encodé (compressé)
        StringBuilder result = new StringBuilder();

        // On dépose les caractères à encoder dans un tableau
        char[] arrayToEncode = toEncode.toCharArray();
        // Passer à travers chaque caractère et l'encoder dans la variable result
        for (int i = 0; i < arrayToEncode.length; i++) {
            result.append(huffmanCode[arrayToEncode[i]]);
        }
        return result.toString();
    }

    // Ça serait mieux avec une liste prioritaire, mais ça fonctionne et j'ai pas le temps de le modifier.
    private static String[] buildHuffmanTree(String toEncode) {
        // liste d'objet de type NoeudHuffman contenant les caractères et leurs fréquences
        SortedLinkedList<NodeHuffman> listeCaractere = new SortedLinkedList<NodeHuffman>();

        // Passer à travers tous les caractères de la String et les ajoutés à notre structure
        for (int i = 0, size = toEncode.length(); i < size; i++) {
            int index = listeCaractere.add(new NodeHuffman(toEncode.charAt(i), 1));
            if (index >= 0) {
                int freq = listeCaractere.remove(index).getFreq() + 1;
                listeCaractere.add(new NodeHuffman(toEncode.charAt(i), freq));
            }
        }
        return buildHuffmanTree(listeCaractere);
    }

    /**
     * Construis l'arbre de Huffman et retourne un tableau contenant les codes
     * pour encoder le fichier.
     * 
     * @param list
     *            la liste trié de noeuds de Huffman contenant les caractères
     *            et leurs fréquences. Le caractère le moins fréquent est à
     *            la fin de la liste.
     * 
     * @return le tableau de {@code String} contenant les codes pour l'encodage.
     * */
    private static String[] buildHuffmanTree(SortedLinkedList<NodeHuffman> list) {
        while (list.size() > 1) {
            // Prends les 2 éléments les plus petit de la liste
            NodeHuffman right = list.remove(list.size() - 1);
            NodeHuffman left = list.remove(list.size() - 1);

            // Crée un nouveau noeuds parents avec left et right comme enfant
            NodeHuffman parent = new NodeHuffman(right.getChar(), left.getFreq() + right.getFreq(), left, right);

            // Ré-insère le nouveau noeud dans la liste
            list.add(parent);
        }

        NodeHuffman root = list.get(0);

        String[] listeCodeHuffman = new String[MAX_NUM_CODE];
        getCodeArray(root, new StringBuilder(), listeCodeHuffman);
        return listeCodeHuffman;
    }

    /**
     * Méthode interne récursive qui parcours l'arbre de Huffman et créé le
     * tableau des codes de Huffman. Le tableau est "retourné" par référence.
     * */
    private static void getCodeArray(NodeHuffman tree, StringBuilder code, String[] listeCodeHuffman) {
        if (tree.isLeaf()) {
            // ajouter le code compressé de Huffman pour ce caractère à l'index du code ASCII
            listeCodeHuffman[tree.getChar()] = code.toString();

        } else {
            // Aller vers l'enfant de gauche
            code.append('1');
            getCodeArray(tree.getLeft(), code, listeCodeHuffman);
            code.deleteCharAt(code.length() - 1);

            // Aller vers l'enfant de droite
            code.append('0');
            getCodeArray(tree.getRight(), code, listeCodeHuffman);
            code.deleteCharAt(code.length() - 1);
        }
    }
}
