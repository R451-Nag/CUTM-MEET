import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-whiteboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './whiteboard.component.html',
  styleUrls: ['./whiteboard.component.css']
})
export class WhiteboardComponent {
  points: { x: number, y: number, color: string }[] = [];
  private drawing = false;
  currentColor: string = 'black'; // Default drawing color
  isEraser: boolean = false; // Toggle for eraser mode

  // Array of colors for selection
  colors: string[] = ['black', 'red', 'blue', 'green', 'yellow', 'orange', 'purple'];

  startDrawing(event: MouseEvent) {
    this.drawing = true;
    this.addPoint(event);
  }

  draw(event: MouseEvent) {
    if (!this.drawing) return;
    this.addPoint(event);
  }

  stopDrawing() {
    this.drawing = false;
  }

  addPoint(event: MouseEvent) {
    const rect = (event.target as HTMLElement).getBoundingClientRect();
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;

    if (this.isEraser) {
      // Erasing by setting the color to white (assuming the background is white)
      this.points.push({ x, y, color: 'white' });
    } else {
      this.points.push({ x, y, color: this.currentColor });
    }
  }

  clearBoard() {
    this.points = [];
  }

  setColor(color: string) {
    this.currentColor = color; // Update current drawing color
    this.isEraser = false; // Ensure eraser mode is off when selecting a color
  }

  toggleEraser() {
    this.isEraser = !this.isEraser; // Toggle eraser mode
    if (this.isEraser) {
      this.currentColor = 'white'; // Set current color to white for erasing
    }
  }
}
