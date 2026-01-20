namespace ParallelProgram
{
    public class SequentialMergeSort
    {
        public static void Sort(int[] array)
        {
            if (array == null || array.Length <= 1)
            {
                return;
            }

            int mid = array.Length / 2;
            int[] left = array.Take(mid).ToArray();
            int[] right = array.Skip(mid).ToArray();

            Sort(left);
            Sort(right);
            Merge(array, left, right);
        }

        private static void Merge(int[] result, int[] left, int[] right)
        {
            int i = 0;
            int j = 0;
            int k = 0;

            while (i < left.Length && j < right.Length)
            {
                if (left[i] <= right[j])
                {
                    result[k++] = left[i++];
                }
                else
                {
                    result[k++] = right[j++];
                }
            }
            while (i < left.Length)
            {
                result[k++] = left[i++];
            }
            while (j < right.Length)
            {
                result[k++] = right[j++];
            }
        }
    }
}
