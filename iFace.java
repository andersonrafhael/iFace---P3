package iface;



import java.util.Scanner;
import java.io.IOException;



public class IFace {
    
    static int UsersNumber = 0;
    static String Users[] = new String[50];
    static String UserNames[] = new String[50];
    static String Passwords[] = new String[50];
    static int LoggedUser = -1;
    static String Communities[] = new String[10];
    static int NumFriends[] = new int[50];
    static int IsFriend[][] = new int [50][50];
    static int NumPendingRequests[] = new int [49];
    static int PendingRequests[][] = new int[49][49];
    
    
     public final static void ClearConsole(){

        try {
            final String os = System.getProperty("os.name");
            

            if (os.contains("Windows 8.1")){
                System.out.printf("%s", os);
                Runtime.getRuntime().exec("cls");

            }
            
            else{
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e){
        }
    }
     
    public static void ClearConsole1() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
     
    public static void Exit() {
        
        int option;
        Scanner input = new Scanner(System.in);
        
        System.out.println("\t\t\t*** iFace ***");
        System.out.println("\n\nTem certeza que deseja ir embora? :(");
        System.out.println("[1] Sim\n[2] Não\n\n");
        
        option = input.nextInt();
        
        if(option == 1) {
            ClearConsole1();
            System.out.println("Até logo :)");
            System.exit(0);
        }
        else {
            ClearConsole1();
            SignInOrSignUp();
        }
    }
    
    public static int GetLoggedUserIndex(String login, String password) {
        int i;
        boolean exist = false;
        
        for(i=0; i<50; i++) {
            if(Users[i] != null) {
                if(login.equals(Users[i])) {
                    exist = true;
                    break;
                }
            }
        }
  
        if(exist == true) {
            if(password.equals(Passwords[i])) {
                return i;
            }
        }
    return -1;
    }
    
    public static void EditData() {
        int option, option1;
        String NewLogin, NewPassword, NewName;
        Scanner input = new Scanner(System.in);
        option = input.nextInt();
        input.nextLine();
        
        switch (option) {
                
           case 1:
               ClearConsole1();
               System.out.println("\t\t\t*** Alterar Dados ***");
               System.out.printf("\nSeu login atual é: %s", Users[LoggedUser]);
                while(true) {
                    System.out.println("\nDigite um novo login: ");
                    System.out.print(">> ");
                    NewLogin = input.nextLine();
                    if (Verify_Login(NewLogin)) 
                        System.out.println("\nEsse usuário já existe. Tente novamente!");
                    else {
                        Users[LoggedUser] = NewLogin;
                        ClearConsole1();
                        System.out.println("\nSeu Login foi alterado com sucesso!\n");
                        EditProfile();
                        break;
                    }
                } 
                 
                
            case 2:
                ClearConsole1();
                System.out.println("\t\t\t*** Alterar Dados ***");
                System.out.printf("\nSeu Nome é: %s", UserNames[LoggedUser]);
                
                System.out.println("\nComo você quer ser chamado a partir de agora?");
                System.out.print(">> ");
                NewName = input.nextLine();
                    
                UserNames[LoggedUser] = NewName;
                ClearConsole1();
                System.out.println("\nSeu Nome foi alterado com sucesso!\n");
                EditProfile();
                break;
                
            case 3:
                int i;
                String PassConfirmation = null;
                ClearConsole1();
                
                System.out.println("\t\t\t*** Alterar Dados ***");
                System.out.printf("\nSua senha atual é: ");
                
                for(i = 0; i < Passwords[LoggedUser].length(); i++) 
                    System.out.print("*");
                System.out.println("\nInforme-a para continuar:");
                System.out.print(">> ");
                PassConfirmation = input.nextLine();
                
                if(PassConfirmation.equals(Passwords[LoggedUser])) {
                    while(true) {
                        System.out.println("Digite uma nova senha: ");
                        System.out.print(">> ");
                        Passwords[LoggedUser] = input.nextLine();       
                        System.out.println("Confirme sua senha: ");
                        System.out.print(">> ");

                        PassConfirmation = input.nextLine();
                        if(Passwords[LoggedUser].equals(PassConfirmation)) {
                            ClearConsole1();
                            System.out.println("\nSua senha foi alterada com sucesso!\n");
                            EditProfile();
                            break;   
                        }
                            
                        else
                            System.out.println("As senhas não coincidem! Tente novamente.");
                    }
                } 
                
            case 4:
                ClearConsole1();
                System.out.println("\t\t\t*** Deletar Conta ***");
                System.out.println("Tem certeza que deseja deletar sua conta? :(");
                System.out.println("[1] Sim\n[2] Não");
                option1 = input.nextInt();
                input.nextLine();
                
                switch(option1) {
                    case 1:
                        Users[LoggedUser] = null;
                        UserNames[LoggedUser] = null;
                        Passwords[LoggedUser] = null;
                        NumFriends[LoggedUser] = 0;
                        NumPendingRequests[LoggedUser] = 0;
                        UsersNumber--;
                        
                        for(i=0; i<49; i++) {
                            PendingRequests[LoggedUser][i] = 0;
                            if(PendingRequests[i][LoggedUser] == 1) {
                                PendingRequests[i][LoggedUser] = 0;
                                NumPendingRequests[i]--;
                            }
                            
                            if(IsFriend[i][LoggedUser] == 1) {
                                IsFriend[LoggedUser][i] = 0;
                                IsFriend[i][LoggedUser] = 0;
                                NumFriends[i]--;                            
                            }
                        }
                        
                        ClearConsole1();
                        System.out.println("Conta desativada com sucesso\n");
                        SignInOrSignUp();
                    case 2:
                        ClearConsole1();
                        EditProfile();
                }
                             
            case 5:
                ClearConsole1();
                UserScreen(); 
        }  
    }
    
    public static void EditProfile() {
        System.out.println("\t\t\t *** Alterar Dados ***");
        System.out.println("\nEscolha uma das opções abaixo\n");
        System.out.println("\n[1] Login\n[2] Nome\n[3] Senha\n[4] Deletar Conta \n[5] Voltar para tela incial");
        EditData();
    }
    
    public static int GetIndexFriend(String username) {
        int i;
        for(i=0; i<50; i++) {
            if(UserNames[i] != null && UserNames[i].equals(username))
                return i;
        }
        return -1;
    }
    
    public static void MessageMenu() {
        
        int option;
        Scanner input = new Scanner(System.in);
        
        System.out.println("\t\t\t*** Mensagens ***");
        System.out.println("\n[1] Enviar Mensagem\n[2] Voltar");
        
        option = input.nextInt();
        input.nextLine();
        
        switch(option) {
            case 1:
                ClearConsole1();
                FriendsMenu();
            
            case 2:
                ClearConsole1();
                FriendsMenu();
        }
        
    }
    
    public static void Connections() {
        int option, option1, option2, i;
        Scanner input = new Scanner(System.in);
        option = input.nextInt();
        input.nextLine();
        
        switch (option) {
                
           case 1:
               ClearConsole1();             
               System.out.println("\t\t\t*** Amigos ***");
               
                if (NumFriends[LoggedUser] == 0) {
                    System.out.println("\nVocê ainda não possui Conexões Ativas");
                    System.out.println("\nTorne-se amigo de algumas pessoas e volte aqui para poder visualizá-las! :)");
                    System.out.println("[1] Voltar às Conexões\n[2] Voltar à tela incial");
                    
                    option1 = input.nextInt();
                    input.nextLine();
                    
                    switch(option1) {
                        case 1:
                            ClearConsole1();
                            FriendsMenu();
                            break;
                        case 2:
                            ClearConsole1();
                            UserScreen();
                            break;
                    }
                }
                        
                else {
                    System.out.println("Minhas Conexões: ");
                    for(i = 0; i<50; i++) {
                        if (IsFriend[LoggedUser][i] == 1) 
                            System.out.printf("%d. %s\n", i, UserNames[i]);                   
                    }
                    MessageMenu();
                }
                                               
            case 2:
                if(NumPendingRequests[LoggedUser] == 0) {
                    ClearConsole1();
                    System.out.println(">> Você não possui solicitações pendentes.\n");
                    FriendsMenu();
                
                }
                else {
                    ClearConsole1();
                    System.out.println("\t\t\t*** Solicitações Pendentes ***");
                    System.out.printf("\n%s, você possui %d solicitações pendentes. =D", UserNames[LoggedUser], NumPendingRequests[LoggedUser]);
                    //System.out.print("\nVeja quem quer se tornar seu amigo: \n");
                    
                    for(i=0; i<50; i++) {
                        if(PendingRequests[LoggedUser][i] == 1) {
                            System.out.printf("\nO usuário '%s' deseja se tornar seu amigo. Permitir?", UserNames[i]);
                            System.out.println("\n[1] Sim\n[2] Não agora\n[3] Rejeitar");
                            
                            option2 = input.nextInt();
                            input.nextLine();
                            
                            switch(option2) {
                                case 1:
                                    System.out.printf("\nQue ótimo! Agora você e %s são amigos.\n\n", UserNames[i]);
                                    NumFriends[LoggedUser]++;
                                    NumFriends[i]++;
                                    IsFriend[LoggedUser][i] = 1;
                                    IsFriend[i][LoggedUser] = 1;
                                    NumPendingRequests[LoggedUser]--;
                                    PendingRequests[LoggedUser][i] = 0;
                                    FriendsMenu();
                                
                                case 2:
                                    ClearConsole1();
                                    System.out.println("\nOk :)\n");
                                    FriendsMenu();
                                    
                                case 3:
                                    System.out.println("\nSolicitação recusada\n");
                                    NumPendingRequests[LoggedUser]--;
                                    PendingRequests[LoggedUser][i] = 0;
                                    FriendsMenu();
                            }
                        }                       
                    }                   
                }
                
            case 3:
                
                String username;
                int IndexFriend;

                if(UsersNumber <= 1) {
                    ClearConsole1();
                    System.out.println("Ainda não há outros usuários ativos no momento :(\n");
                    FriendsMenu();                   
                }
                
                else {
                    ClearConsole1();
                    System.out.println("Usuários cadastrados no iFace:");
                    for(i=0; i<50; i++) {
                        if(Users[i] != null) {
                            if((i != LoggedUser) && (PendingRequests[LoggedUser][i] != 1 || PendingRequests[i][LoggedUser] != 1) && (IsFriend[LoggedUser][i] != 1 || IsFriend[i][LoggedUser] != 1)) {
                                System.out.printf("* %s\n", UserNames[i]);
                            }     
                        }
                    }
                   
                    while (true) {
                        System.out.print("\n\n");
                        System.out.println("Para quem você deseja enviar uma Solicitação de Amizade?");
                        System.out.print(">> ");

                        username = input.nextLine();
                        
                        IndexFriend = GetIndexFriend(username);
                        
                        if(IndexFriend != -1) {
                            PendingRequests[IndexFriend][LoggedUser] = 1;
                            NumPendingRequests[IndexFriend]++;
                            break;
                        }
                        else
                            System.out.println("Nome de usuário não encontrado. Tente novamente!");
                    }
                    
                    ClearConsole1();
                    System.out.println("Solicitação de Amizada enviada!");  
                    FriendsMenu();
                }
                                              
            case 4:
                ClearConsole1();
                UserScreen(); 
        }  
    }
    
    public static void FriendsMenu() {
        
        System.out.println("\t\t\t *** iFace NetWork ***");
        //System.out.println("\nEscolha uma das opções abaixo\n");
        System.out.println("\n[1] Minhas Conexões\n[2] Solicitações Pendentes\n[3] Enviar Solicitação de Amizade\n[4] Voltar para tela incial");
        Connections();
        
    }
    
    public static void Messages() {
        
    }
    
    public static void CommunitiesMenu() {
        
    }
     
    public static void UserScreen() {
        System.out.println("\t\t\t *** iFace ***");
        System.out.printf("Olá, %s :)\nO que vamos fazer agora?\n", UserNames[LoggedUser]);
         
        int option;
        Scanner input = new Scanner(System.in);
        boolean restart = true;
        
         while(restart) {
            System.out.println("\n[1] Editar perfil\n[2] Amigos\n[3] Mensagens\n[4] Comunidades\n[5] Desconectar-se");
            option = input.nextInt();
            switch (option) {
                case 1:
                    ClearConsole1();
                    EditProfile(); 
                    break;
                case 2:
                    ClearConsole1();
                    FriendsMenu();
                    break;
                case 3:
                    ClearConsole1();
                    Messages();
                case 4:
                    CommunitiesMenu();
                    Exit();     
                case 5:
                    ClearConsole1();
                    SignInOrSignUp(); 
            }
        }       
    }
    
    public static void Login() {
        String user,  password;
        int option;
        boolean restart = true;
        Scanner input = new Scanner(System.in);
        System.out.println("\t\t\t*** Entrar ***");
        
        while(restart) {
            System.out.print("Login: ");
            user = input.nextLine();
            
            System.out.print("Senha: ");
            password = input.nextLine();
            
            //System.out.printf("\nAntes de entrar na função: %d", LoggedUser);
            LoggedUser = GetLoggedUserIndex(user, password);
            //System.out.printf("\nDepois de entrar na função: %d", LoggedUser);
            
            if(LoggedUser == -1) {
                ClearConsole1();
                System.out.println("\t\t\t*** Login ou senha não encontrados ***");
                System.out.println("\n[1] Tentar novamente\n[2] Ainda não tem acesso? Cadastre-se aqui!\n\n\n");
                option = input.nextInt();
                input.nextLine();
                
                switch(option) {
                    case 1:
                        ClearConsole1();
                        Login();
                        break;
                    case 2:
                        ClearConsole1();
                        SignUp();                    
                }        
            }
            
            else {
                ClearConsole1();
                UserScreen();
                restart = false;
            }       
        }        
    }
    
    public static boolean Verify_Login(String user) {
        for (String i : Users) {
            if (i != null && i.equals(user)) 
                return true;
        }
        
    return false;
    }
        
    public static void SignUp() {
        String user = null,  password = null, username, pass_confirmation;
        boolean restart = true;
        int i;
        Scanner input = new Scanner(System.in);
        System.out.println("\t\t\t*** Cadastro de Novo Usuário ***");
        
        while(restart) {
            System.out.println("Digite um novo usuário: ");
            System.out.print(">> ");
            user = input.nextLine();
            if (Verify_Login(user)) {
                System.out.println("Esse usuário já existe. Tente novamente!");
                System.out.print(">> ");
            }
            else
                break;
        } 
        
        System.out.println("Como você gostaria de ser chamado?");
        System.out.print(">> ");
        username = input.nextLine();
        
        while(restart) {
            System.out.println("Digite uma senha: ");
            System.out.print(">> ");
            password = input.nextLine();       
            System.out.println("Confirme sua senha: ");
            System.out.print(">> ");

            pass_confirmation = input.nextLine();
            if(password.equals(pass_confirmation))
                break;
            else
                System.out.println("<< As senhas não coincidem! Tente novamente. >>");
        }
        
        for(i = 0; i < 50; i++) {
            if(Users[i] == null) {
                Users[i] = user;       
                Passwords[i] = password;
                UserNames[i] = username;
                break;
            }
        } 
        
        UsersNumber++;
        System.out.println("\nSeus dados foram salvos com sucesso!");
        System.out.printf("\n\nSeja bem-vindo ao iFace, %s :)\n", UserNames[i]);
              
        ClearConsole1();
        SignInOrSignUp();      
    }
    
    public static void SignInOrSignUp() {
    
        int option;
        Scanner input = new Scanner(System.in);
        boolean restart = true;
        
        
        while(restart) {
            System.out.println("\t\t\t*** Bem-vindo ao iFace *** ");    
            System.out.println("[1] Entrar\n[2] Registrar-se\n[3] Sair");
            option = input.nextInt();
            switch (option) {
                case 1:
                    ClearConsole1();
                    Login(); 
                    break;
                case 2:
                    ClearConsole1();
                    SignUp();
                    break;
                case 3:
                    ClearConsole1();
                    Exit();                       
            }
        }       
    }
        
    public static void main(String[] args) throws IOException {
              
        SignInOrSignUp();
    }   
}
