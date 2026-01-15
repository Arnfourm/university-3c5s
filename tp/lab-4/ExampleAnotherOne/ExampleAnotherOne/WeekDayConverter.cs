using System.Collections;

namespace dayConverter
{
    public class WeekDayConverter
    {
        public static string GetNameByDay(int dayNumber)
        {
            Hashtable week = new Hashtable();
            
            week[1] = "Понедельник";
            week[2] = "Вторник";
            week[3] = "Среда";
            week[4] = "Четверг";
            week[5] = "Пятница";
            week[6] = "Суббота";
            week[7] = "Воскресенье";

            string result = week[dayNumber] as string;
            if (result == null)
            {
                throw new ArgumentException();
            }
            else
            {
                return result;
            }
        }
    }
}
