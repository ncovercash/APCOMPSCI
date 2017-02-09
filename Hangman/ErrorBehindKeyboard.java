public class ErrorBehindKeyboard extends RuntimeException {
  public ErrorBehindKeyboard() { super(); }
  public ErrorBehindKeyboard(String message) { super(message); }
  public ErrorBehindKeyboard(String message, Throwable cause) { super(message, cause); }
  public ErrorBehindKeyboard(Throwable cause) { super(cause); }
}