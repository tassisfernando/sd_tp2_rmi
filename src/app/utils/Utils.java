package app.utils;

public class Utils {

    // Messages
    public static final byte CONNECT = (byte) 1;
    public static final String DISCONNECT = "D";
    public static final String WELCOME_MSG = "Bem-vindo ao chat!";
    public static final String WAITING_USERS = "Aguardando outro usuário conectar...";
    public static final String WAITING_START = "Aguardando início...";
    public static final String WAITING_RESPONSE = "Aguardando resposta...";
    public static final String START_CHAT = "Inicie a conversa:";
    public static final String END_CHAT = "O outro usuário saiu do chat!";
    public static final String SEND_MESSAGE = "Envie sua mensagem: (Clique em cancelar para sair)";

    // Server
    public static final int SERVER_PORT = 4002;
    public static final String LOCAL_URI = "rmi://localhost:"+ SERVER_PORT;
    public static final String SERVER_NAME = LOCAL_URI+"/Server";

    public static final int MAX_USERS = 2;
}
