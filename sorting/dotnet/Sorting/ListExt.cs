using System.Collections.Generic;
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
}