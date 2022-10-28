package discord_bot;

import java.util.ArrayList;
import javax.security.auth.login.LoginException;
import java.util.List;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {

	public static JDA jda;
    public static String prefix = "$";
    public static String prefix1 = "give";
    
    public static void main(String[] args) throws LoginException {
        List<GatewayIntent> gatewayIntents = new ArrayList<>();
        gatewayIntents.add(GatewayIntent.GUILD_MEMBERS);
    
        
        jda = new JDABuilder(AccountType.BOT).setToken("OTIzNjE5ODIxMDE3NTA5OTA5.YcSp_Q.AqnEygOGjjgMpsDVOaQNsuiOI2M").build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.streaming("Poop", "work"));
        
        
        jda.addEventListener(new Responses());
    }
}
