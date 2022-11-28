/* -------------------------------------------------------------------------
   | Auteur(s) :   Eric Perron        IFT2810                              |
   |-----------------------------------------------------------------------|
   | Dernière mise à jour : 18 novembre 2014                               |
   |-----------------------------------------------------------------------|
 */

package question2;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Cette classe est le point d'entrée du programme. Elle ne contient que la
 * méthode {@code main} qui exécute les méthodes des autres classes afin de
 * produire le résultat attendu.
 * */
public class Main {

    public static void main(String[] args) {

        // Objet contenant les méthodes principales de notre programme
        Programme programme = new Programme();

        // La chaîne de caractère lu dans le fichier
        String text = null;

        // Lecture du fichier LoremIpsum.bin
        try {
            text = programme.readFile("LoremIpsum.bin");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (text != null) {
            // On encode chaque caractère et on enregistre le fichier compressé
            String compressedText = Huffman.encode(text);
            programme.writeFile(compressedText, "compressedLoremIpsum.bin");
        }
    }
}

/*
 * Questions supplémentaires :
 * 
 * Nombre de bits du fichier compressé : 42 882 octets
 * Ratio de compression : 42 882 / 80 256 = 0,5343 ou 53,43 %
 * 
 * Question bonus :
 * Non. Si un attaquant trouve l'arbre de Huffman, il sera facile de décoder le message.
 * S'il intercèpte le message et qu'il n'a pas l'arbre, il peut facilement essayer les arbres
 * les plus commun dans les différentes langues (français, anglais, etc.). Dès qu'il aura trouvé
 * quelques lettres qui font du sens, il pourra décoder le message.
 */
