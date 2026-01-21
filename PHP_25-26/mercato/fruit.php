<?php
class Fruit {
    public $name, $color, $taste, $quantity;

    // costruttore
    function __construct($name, $color, $taste, $quantity) {
        $this->name = $name;
        $this->color = $color;
        $this->taste = $taste;
        $this->quantity = $quantity;
    }

    // getter
    function get_name() {
        return $this->name;
    }
    function get_color() {
        return $this->color;
    }
    function get_taste() {
        return $this->taste;
    }
    function get_quantity() {
        return $this->quantity;
    }

    // setter
    function set_name($name) {
        $this->name = $name;
    }
    function set_color($color) {
        $this->color = $color;
    }
    function set_taste($taste) {
        $this->taste = $taste;
    }
    function set_quantity($quantity) {
        $this->quantity = $quantity;
    }

    // stampa info elemento
    public function print_info() {
        // nota: ENT_QUOTES serve per stampare in modo sicuro anche dentro gli attributi HTML
        $name  = htmlspecialchars($this->name, ENT_QUOTES);
        $color = htmlspecialchars($this->color, ENT_QUOTES);
        $taste = htmlspecialchars($this->taste, ENT_QUOTES);
        $quantity = (int)$this->quantity;

        echo
        "
            <div class='card'>
                <div class='card__top'>
                    <span class='badge badge--$color'></span>
                    <h2 class='card__title'>$name</h2>
                </div>
                <p class='card__meta'>Color: <b>$color</b></p>
                <p class='card__meta'>Taste: <b>$taste</b></p>
                <div class='card__meta'>
                    Quantity: <b>$quantity</b>
                    <form method='post' style='display:inline-block; margin-left:10px;'>
                        <input type='hidden' name='fruit_name' value='$name'>
                        <button type='submit' name='delta' value='-1' aria-label='decrease quantity'>-</button>
                        <button type='submit' name='delta' value='1' aria-label='increase quantity'>+</button>
                    </form>
                </div>
            </div>
        ";
    }
}
