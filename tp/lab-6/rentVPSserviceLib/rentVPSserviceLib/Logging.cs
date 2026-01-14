namespace rentVPSserviceLib
{
    public class Logging
    {
        public static string FormattingLogWatchMessage(string methodName, Guid id)
        {
            return $"[{DateTime.Now:HH:mm:ss}] [{methodName}] Succes: object with id {id} was watched";
        }

        public static string FormattingLogCreateMessage(string methodName, Guid id)
        {
            return $"[{DateTime.Now:HH:mm:ss}] [{methodName}] Succes: object with id {id} was created";
        }

        public static string FormattingLogUpdateMessage(string methodName, Guid id)
        {
            return $"[{DateTime.Now:HH:mm:ss}] [{methodName}] Succes: object with id {id} was updated";
        }

        public static string FormattingLogDeleteMessage(string methodName, Guid id)
        {
            return $"[{DateTime.Now:HH:mm:ss}] [{methodName}] Succes: object with id {id} was deleted";
        }

        public static string FormattingLogNoExist(string methodName, Guid id)
        {
            return $"[{DateTime.Now:HH:mm:ss}] [{methodName}] Error: object with id {id} not found";
        }

        public static string FormattingLogSaveError(string methodName)
        {
            return $"[{DateTime.Now:HH:mm:ss}] [{methodName}] Error: error while trying to save changes";
        }
    }
}
