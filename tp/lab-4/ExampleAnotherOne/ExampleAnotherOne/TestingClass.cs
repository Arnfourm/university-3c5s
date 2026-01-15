using NUnit.Framework;

namespace dayConverter
{
    [TestFixture]
    public class TestingClass
    {
        [Test]
        public void TestGetDayOfWeekName()
        {
            Assert.That(WeekDayConverter.GetNameByDay(1), Is.EqualTo("Понедельник"));
            Assert.That(WeekDayConverter.GetNameByDay(2), Is.EqualTo("Вторник"));
            Assert.That(WeekDayConverter.GetNameByDay(3), Is.EqualTo("Среда"));
            Assert.That(WeekDayConverter.GetNameByDay(4), Is.EqualTo("Четверг"));
            Assert.That(WeekDayConverter.GetNameByDay(5), Is.EqualTo("Пятница"));
            Assert.That(WeekDayConverter.GetNameByDay(6), Is.EqualTo("Суббота"));
            Assert.That(WeekDayConverter.GetNameByDay(7), Is.EqualTo("Воскресенье"));
        }

        [Test]
        public void TestGetDayOfWeekNameInvalidArgument()
        {
            Assert.That(() => WeekDayConverter.GetNameByDay(8),
                Throws.TypeOf<ArgumentException>());
        }
    }
}
