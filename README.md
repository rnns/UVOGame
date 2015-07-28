Tutorial ANDROID GRAPHICS

Esse tutorial tem como objetivo auxiliar a impressão de imagens, textos ou outros objetos na tela do seu aplicativo android, de forma simples, abordando aspectos pertinentes da biblioteca andoird.graphics. Os exemplos que seguem serão extraídos de um jogo, 2D, relativamente simples, que se utilizada de classes desta biblioteca.
    Dentre as demais bibliotecas utilizadas, a android.graphics se destaca pois ela é quem faz o desenho final na tela de todo o backend que nos desenvolvemos para passar a impressão de que as imagens/bitmaps realmente estão se mexendo.
    As principais classes que utilizamos foram:

Bitmap / BitmapFactory
Paint
Canvas

    Bitmap / BitmapFactory

A classe BitmapFactory cria bitmaps de diversos objetos, como arquivos, streams e byte-arrays. Bitmaps contém a descrição de imagens pixel por pixel, diferentemente de imagens que possuem gráficos vetoriais. 

Estão disponíveis diversos métodos para fazer essa criação, no nosso caso, utilizamos o método BitmapFactory.decodeResource(), como se segue:


Neste caso, estaremos transformando a imagem bg.png (background), que foi fornecida por nos, e depois passamos ela como parâmetro para o objeto background, que posteriormente será desenhada na tela através da classe canvas.

Já a classe Bitmap possui diversos métodos para fazer a manipulação de um bitmap que já existe, dentre os métodos existentes podemos citar compress, que tenta comprimir um bitmap.

Paint 

A classe Paint é utilizada para armazenar informações sobre cores e fontes de como pintar desenhos geométricos na tela, textos ou até mesmo bitmaps. Seu fluxo de utilização é da seguinte forma:

Instanciamos um objeto do tipo Paint
Em seguida, fazemos todas as definições necessárias, como cor, tamanho da fonte. Todas as definições que são possíveis podem ser encontradas aqui (http://developer.android.com/reference/android/graphics/Paint.html)
Por ultimo, podemos utilizar as definições feitas nesta instância como parâmetro para um objeto do tipo canvas, que será analisado a seguir.







No trecho de código acima, essas três frases serão desenhadas no inicio do jogo, e todas possuirão as definições feitas no objeto paint, ou seja, terão a cor branca, tamanho da fonte 30, e estarão em negrito, segue foto demonstrado:






    Canvas

Por ultimo, mas não menos importante, vamos ver um pouco de como usar e para que serve a classe canvas. A classe canvas serve como um ‘armazenador’ da tela que será desenhada, e sempre necessita de um bitmap para fazer esse desenho. Existem algumas formas para se usar o canvas, pode-se instanciar um bitmap e passar ele de parâmetro:



Outra opção é quando se utiliza a classe SurfaceHolder (http://developer.android.com/reference/android/view/SurfaceHolder.html), onde se utilza o método lockCanvas para obter um objeto Canvas:



Um exemplo de utilização do canvas, muito semelhante ao código mostrado no começo, é:



Neste código, será desenhado na tela as frases “Você perdeu!”, não posição x = WIDTH / 2-80 e y = HEIGHT /2, com os estilos contidos no objeto paint. O mesmo acontecerá com os outros dois métodos drawText.
Existem diversos outros métodos para desenhar na classe Canvas, como drawBitmap, drawOval, drawLine, etc. Todos os métodos podem ser encontrados aqui: http://developer.android.com/reference/android/graphics/Canvas.html#drawText(java.lang.String, float, float, android.graphics.Paint)

Estas e todas as outras classes da biblioteca android.graphics podem ser encontradas em: http://developer.android.com/reference/android/graphics/package-summary.html

