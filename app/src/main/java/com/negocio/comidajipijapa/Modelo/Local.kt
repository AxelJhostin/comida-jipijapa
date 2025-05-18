package com.negocio.comidajipijapa.Modelo

data class Local(
    val id: String = "",
    val nombre: String = "",
    val horario: String = "",
    val categoria: String = "",
    val telefono: String = "",
    val menuUrl: String = "",     // Enlace al menú en PDF
    val ubicacion: String = "",   // Enlace de Google Maps
    val imagenUrl: String = "",   // Imagen principal del local
    val imagenesExtra: List<String> = emptyList(), // Imágenes adicionales
    val diasAtencion: List<String> = emptyList(), // Días que abre
    val instagram: String = "" //redes sociales para buscar
)

val locales = listOf(
    Local(
        id = "1",
        nombre = "Pizzería Don Mario",
        horario = "12:00 PM - 10:00 PM",
        categoria = "Pizzería",
        telefono = "0987654321",
        menuUrl = "https://drive.google.com/file/d/13Gb6zphmZ9DZ0c9xuQm1CqFfN7WiyXdh/view?usp=sharing",
        ubicacion = "https://maps.google.com/?q=-1.34,-80.02",
        imagenUrl = "https://drive.google.com/uc?export=download&id=1A4aQVIf3bvu4-t7R99ZuliLhrKJJjOTE",
        imagenesExtra = listOf(
            "https://drive.google.com/uc?export=download&id=1A4aQVIf3bvu4-t7R99ZuliLhrKJJjOTE",
            "https://drive.google.com/uc?export=download&id=1A4aQVIf3bvu4-t7R99ZuliLhrKJJjOTE",
            "https://drive.google.com/uc?export=download&id=1A4aQVIf3bvu4-t7R99ZuliLhrKJJjOTE",
            "https://drive.google.com/uc?export=download&id=1A4aQVIf3bvu4-t7R99ZuliLhrKJJjOTE"
        ),
        diasAtencion = listOf("Lunes", "Martes", "Miércoles", "Viernes", "Sábado"),
        instagram = "https://www.instagram.com/pucemanabi?utm_source=ig_web_button_share_sheet&igsh=ZDNlZDc0MzIxNw=="
    ),
    Local(
        id = "2",
        nombre = "Pollo a la Brasa El Buen Gusto",
        horario = "11:00 AM - 9:00 PM",
        categoria = "Parrillada",
        telefono = "0976543210",
        menuUrl = "https://drive.google.com/file/d/13Gb6zphmZ9DZ0c9xuQm1CqFfN7WiyXdh/view?usp=sharing",
        ubicacion = "https://maps.google.com/?q=-1.33,-80.01",
        imagenUrl = "https://drive.google.com/uc?export=download&id=10H6KmeydbGj0yk5A16Lz2wiX1YslPW-e",
        imagenesExtra = listOf(
            "https://drive.google.com/uc?export=download&id=10H6KmeydbGj0yk5A16Lz2wiX1YslPW-e",
            "https://drive.google.com/uc?export=download&id=10H6KmeydbGj0yk5A16Lz2wiX1YslPW-e",
            "https://drive.google.com/uc?export=download&id=10H6KmeydbGj0yk5A16Lz2wiX1YslPW-e",
            "https://drive.google.com/uc?export=download&id=10H6KmeydbGj0yk5A16Lz2wiX1YslPW-e"
        ),
        diasAtencion = listOf("Martes", "Jueves", "Viernes", "Domingo"),
        instagram = "https://www.instagram.com/pucemanabi?utm_source=ig_web_button_share_sheet&igsh=ZDNlZDc0MzIxNw=="
    ),
    Local(
        id = "3",
        nombre = "Otra cosa",
        horario = "11:00 AM - 4:00 PM",
        categoria = "Variado",
        telefono = "0976543210",
        menuUrl = "https://drive.google.com/file/d/13Gb6zphmZ9DZ0c9xuQm1CqFfN7WiyXdh/view?usp=sharing",
        ubicacion = "https://maps.google.com/?q=-1.33,-80.01",
        imagenUrl = "https://drive.google.com/uc?export=download&id=1ZkiCSxdm8Yg2IVV4mdbKUZHyynHCY_bz",
        imagenesExtra = listOf(
            "https://drive.google.com/uc?export=download&id=1ZkiCSxdm8Yg2IVV4mdbKUZHyynHCY_bz",
            "https://drive.google.com/uc?export=download&id=1ZkiCSxdm8Yg2IVV4mdbKUZHyynHCY_bz",
            "https://drive.google.com/uc?export=download&id=1ZkiCSxdm8Yg2IVV4mdbKUZHyynHCY_bz",
            "https://drive.google.com/uc?export=download&id=1ZkiCSxdm8Yg2IVV4mdbKUZHyynHCY_bz"
        ),
        diasAtencion = listOf("Martes", "Jueves", "Viernes", "Domingo"),
        instagram = "https://www.instagram.com/pucemanabi?utm_source=ig_web_button_share_sheet&igsh=ZDNlZDc0MzIxNw=="
    ),
    Local(
        id = "4",
        nombre = "Cosa 4",
        horario = "11:00 AM - 9:00 PM",
        categoria = "Comida Rápida",
        telefono = "0976543210",
        menuUrl = "https://drive.google.com/file/d/13Gb6zphmZ9DZ0c9xuQm1CqFfN7WiyXdh/view?usp=sharing",
        ubicacion = "https://maps.google.com/?q=-1.33,-80.01",
        imagenUrl = "https://drive.google.com/uc?export=download&id=1tYqQAsLGK2Szj583iLmeQGhWm8ZRV6O_",
        imagenesExtra = listOf(
            "https://drive.google.com/uc?export=download&id=1tYqQAsLGK2Szj583iLmeQGhWm8ZRV6O_",
            "https://drive.google.com/uc?export=download&id=1ZkiCSxdm8Yg2IVV4mdbKUZHyynHCY_bz",
            "https://drive.google.com/uc?export=download&id=1tYqQAsLGK2Szj583iLmeQGhWm8ZRV6O_",
            "https://drive.google.com/uc?export=download&id=1ZkiCSxdm8Yg2IVV4mdbKUZHyynHCY_bz"
        ),
        diasAtencion = listOf("Martes", "Jueves", "Viernes", "Domingo"),
        instagram = "https://www.instagram.com/pucemanabi?utm_source=ig_web_button_share_sheet&igsh=ZDNlZDc0MzIxNw=="
    ),
    Local(
        id = "5",
        nombre = "Pollo a la Brasa El Buen Gusto",
        horario = "11:00 AM - 10:00 PM",
        categoria = "Parrillada",
        telefono = "0976543210",
        menuUrl = "https://drive.google.com/file/d/13Gb6zphmZ9DZ0c9xuQm1CqFfN7WiyXdh/view?usp=sharing",
        ubicacion = "https://maps.google.com/?q=-1.33,-80.01",
        imagenUrl = "https://drive.google.com/uc?export=download&id=1UsA1YzXP22TqqaS0vxWZE1btDeGtgF64",
        imagenesExtra = listOf(
            "https://drive.google.com/uc?export=download&id=1tYqQAsLGK2Szj583iLmeQGhWm8ZRV6O_",
            "https://drive.google.com/uc?export=download&id=1UsA1YzXP22TqqaS0vxWZE1btDeGtgF64",
            "https://drive.google.com/uc?export=download&id=1tYqQAsLGK2Szj583iLmeQGhWm8ZRV6O_",
            "https://drive.google.com/uc?export=download&id=1UsA1YzXP22TqqaS0vxWZE1btDeGtgF64"
        ),
        diasAtencion = listOf("Martes", "Jueves", "Viernes", "Domingo"),
        instagram = "https://www.instagram.com/pucemanabi?utm_source=ig_web_button_share_sheet&igsh=ZDNlZDc0MzIxNw=="
    ),
    Local(
        id = "6",
        nombre = "Pollo a la Brasa El Buen Gusto",
        horario = "11:00 AM - 12:00 PM",
        categoria = "Parrillada",
        telefono = "0976543210",
        menuUrl = "https://drive.google.com/file/d/13Gb6zphmZ9DZ0c9xuQm1CqFfN7WiyXdh/view?usp=sharing",
        ubicacion = "https://maps.google.com/?q=-1.33,-80.01",
        imagenUrl = "https://drive.google.com/uc?export=download&id=1sy0bYrzsHNJlsTay0Id2d4T28GPjLuEV",
        imagenesExtra = listOf(
            "https://drive.google.com/uc?export=download&id=1sy0bYrzsHNJlsTay0Id2d4T28GPjLuEV",
            "https://drive.google.com/uc?export=download&id=1UsA1YzXP22TqqaS0vxWZE1btDeGtgF64",
            "https://drive.google.com/uc?export=download&id=1sy0bYrzsHNJlsTay0Id2d4T28GPjLuEV",
            "https://drive.google.com/uc?export=download&id=1UsA1YzXP22TqqaS0vxWZE1btDeGtgF64"
        ),
        diasAtencion = listOf("Martes", "Jueves", "Viernes", "Domingo"),
        instagram = "https://www.instagram.com/pucemanabi?utm_source=ig_web_button_share_sheet&igsh=ZDNlZDc0MzIxNw=="
    )
)
