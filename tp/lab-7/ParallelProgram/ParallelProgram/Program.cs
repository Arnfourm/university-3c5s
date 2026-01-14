using System.Diagnostics;

namespace ParallelProgram
{
    class Program
    {
        static void Main()
        {
            Random random = new Random();
            int[] sizes = { 100, 100000, 500000, 1000000, 5000000 };

            foreach (int size in sizes)
            {
                Console.WriteLine($"\nТекущий размер массив - {size}");

                int[] data = new int[size];
                for (int i = 0; i < data.Length; i++)
                {
                    data[i] = random.Next(0, 1000);
                }

                int[] sequenceArray = data;
                Stopwatch sequenceStopWatch = Stopwatch.StartNew();
                SequentialMergeSort.Sort(sequenceArray);
                sequenceStopWatch.Stop();
                Console.WriteLine($"\nБез параллелизма: {sequenceStopWatch.ElapsedMilliseconds} мс");

                int[] parallelArray = data;
                Stopwatch parallelStopWatch = Stopwatch.StartNew();
                ParallelMergeSort.Sort(parallelArray);
                parallelStopWatch.Stop();
                Console.WriteLine($"С параллелизмом: {parallelStopWatch.ElapsedMilliseconds} мс");
            }
        }
    }
}