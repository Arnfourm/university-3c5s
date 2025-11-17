using NUnit.Framework;
using NUnit.Framework.Legacy;

namespace Example
{
    [TestFixture]
    public class TestingClass
    {
        [Test]  
        public void TestGetDayOfWeekName()
        {
            ClassicAssert.AreEqual("Понедельник", WeekDayConverter.GetNameByDay(1));
            ClassicAssert.AreEqual("Вторник", WeekDayConverter.GetNameByDay(2));
            ClassicAssert.AreEqual("Среда", WeekDayConverter.GetNameByDay(3));
            ClassicAssert.AreEqual("Четверг", WeekDayConverter.GetNameByDay(4));
            ClassicAssert.AreEqual("Пятница", WeekDayConverter.GetNameByDay(5));
            ClassicAssert.AreEqual("Суббота", WeekDayConverter.GetNameByDay(6));
            ClassicAssert.AreEqual("Воскресенье", WeekDayConverter.GetNameByDay(7));
        }

        [Test]
        public void TestGetDayOfWeekNameInvalidArgument()
        {
            Assert.That(() => WeekDayConverter.GetNameByDay(8),
                Throws.TypeOf<ArgumentException>());
        }

    }
}
