import { Component } from '@angular/core';
import { WhiteboardComponent } from './whiteboard/whiteboard.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [WhiteboardComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Cutm Meet Whiteboard';
}
