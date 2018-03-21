﻿using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Collections.Generic;

namespace Sorting.Tests
{
    [TestClass]
    public class SortingTests
    {
        [TestMethod]
        public void HeapSorting_MergeSortedLists_EachListHasDifferentAmountOfNumbers()
        {
            var sort = new HeapSort();
            var result = sort.MergeSortedLists(new List<int> { 3 }, new List<int> { 2, 4 });

            CollectionAssert.AreEqual(new List<int> { 2, 3, 4 }, result);
        }

        [TestMethod]
        public void HeapSorting_MergeSortedLists_EachListHasSameAmountOfNumbers()
        {
            var sort = new HeapSort();
            var result = sort.MergeSortedLists(new List<int> { 1, 3 }, new List<int> { 2, 4 });

            CollectionAssert.AreEqual(new List<int> { 1, 2, 3, 4 }, result);
        }

        [TestMethod]
        public void HeapSorting_SimpleListDividesNeatlyIntoTwo()
        {
            ExecuteTestWithSortMethod(new HeapSort(), new List<int> { 4, 3, 2, 1 }, new List<int> { 1, 2, 3, 4 });
        }

        [TestMethod]
        public void HeapSorting_SimpleListUnevenNumbers()
        {
            ExecuteTestWithSortMethod(new HeapSort(), new List<int> { 5, 7, 6, 4, 3, 2, 1 }, new List<int> { 1, 2, 3, 4, 5, 6, 7 });
        }

        private void ExecuteTestWithSortMethod(ISortable sortable, List<int> toSort, List<int> expected)
        {
            var result = sortable.Sort(toSort);

            CollectionAssert.AreEqual(expected, result, "actual: " + result.AsString());
        }
    }
}