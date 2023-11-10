namespace HuffmanCoding;

/// <summary>
/// This class is the program's entry point. It contains only the <c>Main</c> method,
/// which executes methods from other classes to produce the expected result.
/// </summary>
class Program
{
    static void Main()
    {
        string text = BinaryReader.ReadFile("LoremIpsum.bin");

        if (text != null)
        {
            // Encode each character and save the compressed file.
            string compressedText = Huffman.Encode(text);
            File.WriteAllText("compressedLoremIpsum.bin", compressedText);
        }
    }
}
