namespace HuffmanCoding;

using HuffmanCoding.Collections;
using System.Text;

/// <summary>
/// Class containing <c>static</c> methods to encode and decode according to
/// the Huffman algorithm. This class is non-instantiable, as it only contains
/// <c>static</c> methods.
/// </summary>
/// <remarks>
/// This static encoder works for alphabets that contains a maximum of 256 characters
/// </remarks>
internal static class Huffman
{
    /// <summary>
    /// The maximum value of the ASCII code that can be read by this encoder.
    /// </summary>
    private const int MAX_NUM_CODE = 256;

    /// <summary>
    /// This method encodes the characters of the specified string and returns the encoded
    /// version of this string.
    /// </summary>
    /// <param name="toEncode">The string to be encoded.</param>
    /// <returns>The encoded version of the input string.</returns>
    public static string Encode(string toEncode)
    {
        string[] huffmanCodes = BuildHuffmanTree(toEncode);

        return Encode(toEncode, huffmanCodes);
    }

    private static string Encode(string toEncode, string[] huffmanCode)
    {
        StringBuilder result = new();

        // NOTE(PERE): We could use a foreach here.
        for (int i = 0; i < toEncode.Length; i++)
        {
            _ = result.Append(huffmanCode[toEncode[i]]);
        }

        return result.ToString();
    }

    private static string[] BuildHuffmanTree(string toEncode)
    {
        // List containing the characters and their frequencies
        SortedLinkedList<NodeHuffman> listeCaractere = new();

        for (int i = 0; i < toEncode.Length; i++)
        {
            NodeHuffman node = new(toEncode[i], 1);
            int index = listeCaractere.Add(node);
            if (index >= 0)
            {
                node = listeCaractere.Find(node).Value;
                _ = listeCaractere.Remove(node);
                int freq = node.Frequency + 1;
                _ = listeCaractere.Add(new NodeHuffman(toEncode[i], freq));
            }
        }

        return BuildHuffmanTree(listeCaractere);
    }

    // TODO(PERE): We could use System.Collections.Generic.SortedList
    /// <summary>
    /// Construct the Huffman tree and return an array containing the codes used
    /// to encode the file.
    /// </summary>
    /// <param name="list">The list of characters and their frequencies sorted in
    /// descending order by their frequencies</param>
    /// <returns>An array containing the Huffman codes.</returns>
    private static string[] BuildHuffmanTree(SortedLinkedList<NodeHuffman> list)
    {
        while (list.Count > 1)
        {
            NodeHuffman right = list.Last.Value;
            list.RemoveLast();
            NodeHuffman left = list.Last.Value;
            list.RemoveLast();

            NodeHuffman parent = new(right.Character,
                                     left.Frequency + right.Frequency,
                                     left, right);

            _ = list.Add(parent);
        }

        NodeHuffman root = list.ElementAt(0);

        string[] listeCodeHuffman = new string[MAX_NUM_CODE];
        GetCodeArray(root, new StringBuilder(), listeCodeHuffman);
        return listeCodeHuffman;
    }

    /// <summary>
    /// Internal recursive method that traverses the Huffman tree and creates
    /// an array of Huffman codes. The array is "returned" by reference.
    /// </summary>
    /// <param name="tree">The tree containing the Huffman codes.</param>
    /// <param name="code"></param>
    /// <param name="listeCodeHuffman"></param>
    private static void GetCodeArray(NodeHuffman tree, StringBuilder code, string[] listeCodeHuffman)
    {
        if (tree.IsLeaf())
        {
            // ajouter le code compressé de Huffman pour ce caractère à l'index du code ASCII
            listeCodeHuffman[tree.Character] = code.ToString();

        }
        else
        {
            _ = code.Append('1');
            GetCodeArray(tree.Left, code, listeCodeHuffman);
            _ = code.Remove(code.Length - 1, 1);

            _ = code.Append('0');
            GetCodeArray(tree.Right, code, listeCodeHuffman);
            _ = code.Remove(code.Length - 1, 1);
        }
    }
}
