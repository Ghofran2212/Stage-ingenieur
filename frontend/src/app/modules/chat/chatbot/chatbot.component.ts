import { Component } from '@angular/core';
import { ChatService } from '../../../services/chat.service';

@Component({
  selector: 'app-chatbot',
  templateUrl: './chatbot.component.html',
  styleUrls: ['./chatbot.component.css']
})
export class ChatbotComponent {

  question: string = '';
  messages: { sender: string; text: string }[] = [];

  constructor(private chatService: ChatService) {}

  send(): void {

    if (!this.question.trim()) {
      return;
    }

    const message = this.question;

    this.messages.push({
      sender: 'Vous',
      text: message
    });

    this.question = '';

    this.chatService.send(message).subscribe({

      next: (response: string) => {

        this.messages.push({
          sender: 'Bot',
          text: response
        });

      },

      error: (error: any) => {

        console.error(error);

        this.messages.push({
          sender: 'Bot',
          text: 'Impossible de contacter le serveur.'
        });

      }

    });

  }

}