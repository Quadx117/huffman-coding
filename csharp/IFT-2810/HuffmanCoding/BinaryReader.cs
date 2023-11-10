namespace HuffmanCoding;

using System;
using System.Diagnostics;
using System.Text;

/// <summary>
/// This class is meant to read a text file which contains a stream of bits
/// representing ASCII characters, each group of 8 bits being a character.
/// The leftmost bit is the most significant one.
/// </summary>
internal class BinaryReader
{
    /// <summary>
    /// This method reads a file from the hard drive and returns it as a <c>string</c> string.
    /// The format of the file expected by this method is a file containing a sequence of '0's
    /// and '1's with no spaces or line breaks. Each group of 8 characters represents the binary
    /// code of a single character.
    /// </summary>
    /// <param name="filename">The path to the file to be read.</param>
    /// <returns>The <c>string</c> representation of the file's content.</returns>
    public static string ReadFile(string filename)
    {
        StringBuilder result = new();

        try
        {
            string data = File.ReadAllText(filename);
            Debug.Assert(data != null);
            Debug.Assert(data.Length % 8 == 0);

            result.Capacity = data.Length / 8;
            for (int startIndex = 0; startIndex < data.Length; startIndex += 8)
            {
                // NOTE(PERE): This is a bit hacky, but is sufficient for our purposes.
                int charValue = ((data[startIndex + 0] - '0') * (1 << 7)) +
                                ((data[startIndex + 1] - '0') * (1 << 6)) +
                                ((data[startIndex + 2] - '0') * (1 << 5)) +
                                ((data[startIndex + 3] - '0') * (1 << 4)) +
                                ((data[startIndex + 4] - '0') * (1 << 3)) +
                                ((data[startIndex + 5] - '0') * (1 << 2)) +
                                ((data[startIndex + 6] - '0') * (1 << 1)) +
                                ((data[startIndex + 7] - '0') * (1 << 0));
                _ = result.Append((char)charValue);
            }
        }
        catch (Exception e)
        {
            Console.WriteLine(e.Message);
        }

        return result.ToString();
    }
}
