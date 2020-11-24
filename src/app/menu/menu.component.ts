import { Component, OnInit, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  activeMenu: HTMLElement;

  constructor(private renderer:Renderer2) { }

  ngOnInit() {
    this.activeMenu = document.getElementById("inicioID");
  }

  changeActiveMenu(menuElm: HTMLElement){
    this.renderer.removeClass(this.activeMenu, 'active');
    this.renderer.addClass(menuElm, 'active');
    this.activeMenu = menuElm;
  }

}
