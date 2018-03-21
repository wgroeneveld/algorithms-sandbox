using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;

namespace Sorting
{
    public static class ListExt
    {
        public static string AsString(this List<int> list)
        {
            return "(" + string.Join(", ", list.Select(x => x.ToString())) + ")";
        }
    }

    public class HeapSort : ISortable
    {
        public List<int> Sort(List<int> list)
        {
            return SortRecur(list);
        }

        internal List<int> MergeSortedLists(List<int> a, List<int> b)
        {
            var result = new List<int>();
            var aCounter = 0;
            var bCounter = 0;

            Debug.WriteLine("merging" + a.AsString() + " with " + b.AsString());

            while (aCounter < a.Count && bCounter < b.Count)
            {
                var currA = a[aCounter];
                var currB = b[bCounter];

                if (currA < currB)
                {
                    result.Add(currA);
                    aCounter++;
                }
                else
                {
                    result.Add(currB);
                    bCounter++;
                }
            }

            if (aCounter < a.Count)
            {
                result.AddRange(AddRemainder(a, aCounter));
            }
            if (bCounter < b.Count)
            {
                result.AddRange(AddRemainder(b, bCounter));
            }

            return result;
        }

        private List<int> AddRemainder(List<int> list, int index)
        {
            List<int> result = new List<int>();
            for (var i = index; i < list.Count; i++)
            {
                result.Add(list[i]);
            }

            return result;
        }

        private List<int> SortRecur(List<int> list)
        {
            Debug.WriteLine("sorting " + list.AsString());

            if (list.Count == 2)
            {
                return new List<int>
                {
                    list[0] < list[1] ? list[0] : list[1],
                    list[0] < list[1] ? list[1] : list[0]
                };
            }
            if (list.Count <= 1)
            {
                return list;
            }

            var halfIndex = list.Count / 2;

            var left = list.TakeWhile((number, i) => i < halfIndex).ToList();
            var right = list.Except(left).ToList();
            return MergeSortedLists(
                SortRecur(left),
                SortRecur(right));
        }
    }
}