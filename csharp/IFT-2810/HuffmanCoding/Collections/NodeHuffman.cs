namespace HuffmanCoding.Collections;

using System.Collections.Generic;

/// <summary>
/// Class used to create a node to build the search tree for Huffman coding.
/// </summary>
internal class NodeHuffman : IComparable<NodeHuffman>
{
    /// <summary>
    /// The charcater contained in this node.
    /// </summary>
    public char Character { get; private set; }

    /// <summary>
    /// The frequency of the character in this node.
    /// </summary>
    public int Frequency { get; private set; }

    /// <summary>
    /// The left child node.
    /// </summary>
    public NodeHuffman? Left { get; private set; }

    /// <summary>
    /// The right child node.
    /// </summary>
    public NodeHuffman? Right { get; private set; }

    /// <summary>
    /// This constructor calls the fully-parameterized constructor to create a
    /// node whose children are <c>null</c> (to create a leaf, for example).
    /// </summary>
    /// <param name="character">The character of this node.</param>
    /// <param name="frequency">The frequency for this character.</param>
    public NodeHuffman(char character, int frequency) :
        this(character, frequency, null, null)
    {
    }

    /// <summary>
    /// Fully-parameterized constructor used to initialize a new instance of the
    /// <see cref="NodeHuffman"/> class.
    /// </summary>
    /// <param name="character">The character of this node.</param>
    /// <param name="frequency">The frequency for this character.</param>
    /// <param name="left">Left child of this node.</param>
    /// <param name="right">Right child of this node.</param>
    public NodeHuffman(char character, int frequency, NodeHuffman? left, NodeHuffman? right)
    {
        Character = character;
        Frequency = frequency;
        Left = left;
        Right = right;
    }

    /// <summary>
    /// Returns <c>true</c> if this node is a leaf, <c>false</c> otherwise.
    /// </summary>
    /// <remarks>
    /// A leaf is a node with no child at all.
    /// </remarks>
    /// <returns><c>true</c> if this node is a leaf, <c>false</c> otherwise.</returns>
    public bool IsLeaf()
    {
        return Left == null &&
               Right == null;
    }

    /// <summary>
    /// Compares the current instance with another object of the same type and returns
    /// an integer that indicates whether the current instance precedes, follows, or
    /// occurs in the same position in the sort order as the other object.
    /// The comparison is first based on frequency. If the frequency is identical,
    /// then the character value is compared.
    /// </summary>
    /// <param name="other">An object to compare with this instance.</param>
    /// <returns>A value that indicates the relative order of the objects being compared.
    /// The return value has these meanings:<br></br>
    ///     Value – Meaning<br></br>
    ///     Less than zero – This instance precedes other in the sort order.<br></br>
    ///     Zero – This instance occurs in the same position in the sort order as other.<br></br>
    ///     Greater than zero – This instance follows other in the sort order.</returns>
    public int CompareTo(NodeHuffman? other)
    {
        int result = 1;

        if (other != null)
        {
            result = Frequency - other.Frequency;     // Highest frequency first
            if (result == 0)
            {
                result = other.Character - Character; // Smallest ASCII code first if frequencies are equal
            }
        }

        return result;
    }

    /// <summary>
    /// Determines whether the specified object is equal to the current object.
    /// We perform the comparison on the character only. This lets us know if
    /// we have any duplicates.
    /// </summary>
    /// <param name="obj">The object to compare with the current object.</param>
    /// <returns><c>true</c> if the specified object is equal to the current object,
    /// <c>false</c> otherwise.</returns>
    public override bool Equals(object? obj)
    {
        return obj is NodeHuffman otherNode &&
               Character == otherNode.Character;
    }

    /// <summary>
    /// Overriding of the GetHashCode method of the object class in order to
    /// respect the contract with the latter. Not currently used in our implementation.
    /// </summary>
    /// <returns>A hash code for the current object.</returns>
    public override int GetHashCode() => HashCode.Combine(Character);

    public static bool operator ==(NodeHuffman? left, NodeHuffman? right)
    {
        return EqualityComparer<NodeHuffman>.Default.Equals(left, right);
    }

    public static bool operator !=(NodeHuffman? left, NodeHuffman? right)
    {
        return !(left == right);
    }
}
