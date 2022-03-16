import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

public class Main {

    public static void main(final String[] args) {
        //https://discord.com/api/oauth2/authorize?client_id=794212735118082069&scope=bot Este es el link para añadir mi bot a un servidor.
        // Es importante que tenga permisos en dicho servidor porque si no no lo podría meter (por cuestiones de seguridad obvias).
        final String token = "Nzk0MjEyNzM1MTE4MDgyMDY5.X-3iPw.9l5yfMccg6P0YEloDMwxNZ3pRKo";
        //Aqui tendriamos nuestro token. Dicho token se saca creando una aplicación en la página de Developer de Discord, obviamente te tienes que loggear.
        // Dicha aplicación te permitira crear el bot en ella. Comentar tambien que te proporciona id de Aplicación, como una id pública y de cliente. (Aparte del token claro
        final DiscordClient client = DiscordClient.create(token); //Creamos un cliente de Discord (para el bot).
        final GatewayDiscordClient gateway = client.login().block(); //Creamos el gateaway para que dicho cliente se logee.

        gateway.on(MessageCreateEvent.class).subscribe(event -> { //Es una expresión lambda, que utiliza un evento de mensaje para posteriormente trabajr con ello.
            final Message message = event.getMessage(); //Aqui lee los mensajes escritos en los canales. Es un programa muy simple dado que no filtra por canal ni por usuario
            if ("!ping".equals(message.getContent())) { // Método simple que si el mensaje equivale a un !ping, el bot devuelva un !pong en su mismo canal el cual anteriormente ha creado.
                final MessageChannel channel = message.getChannel().block();
                channel.createMessage("Pong!").block();
            }
        });


        gateway.onDisconnect().block(); //Cuando se desconecta del gateaway bloquea al final el bot
    }
}
