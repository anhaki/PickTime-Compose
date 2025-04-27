## ⏰ PickTime - Jetpack Compose Time Wheel Picker

PickTime is a highly customizable and smooth time wheel picker library for Jetpack Compose.  
It updates the value **on every scroll** and provides a super **fluid** user experience! 🚀

![API 21+](https://img.shields.io/badge/API-21%2B-brightgreen.svg)
[![](https://jitpack.io/v/anhaki/PickTime-Compose.svg)](https://jitpack.io/#anhaki/PickTime-Compose)
![Jetpack Compose](https://img.shields.io/badge/Built%20with-Jetpack%20Compose-4285F4.svg?logo=jetpackcompose&logoColor=white)
![License: Apache 2.0](https://img.shields.io/badge/License-Apache_2.0-blue.svg)

---

## 📦 Installation

**1.** Add JitPack to `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // add this line of code 👇
        maven { url = uri("https://jitpack.io") }
    }
}
```

**2.** Add the dependency to `build.gradle.kts`:

```kotlin
implementation("com.github.anhaki:PickTime-Compose:1.1.2")
```

---

## 🚩 Usage

<table>
  <tr>
    <th>
      Code
    </th>
    <th>
      Preview
    </th>
  </tr>

  <tr>
  <td>
              
  ```kotlin
    PickHourMinute(
        initialHour = hour,
        onHourChange = { hour = it },
        initialMinute = minute,
        onMinuteChange = { minute = it },
    )

  ```
  </td>
  <td>  
    <img src="https://raw.githubusercontent.com/anhaki/PickTime-Compose/refs/heads/main/assets/usage_1.gif" > 
  </td>  
  </tr>
  <tr>
  <td>
              
  ```kotlin
    PickHourMinute(
        initialHour = hour,
        onHourChange = { hour = it },
        initialMinute = minute,
        onMinuteChange = { minute = it },
        timeFormat = TimeFormat.HOUR_12,
    )

  ```
  </td>
  <td>  
    <img src="https://raw.githubusercontent.com/anhaki/PickTime-Compose/refs/heads/main/assets/usage_2.gif" > 
  </td>
  </tr>
  <tr>
  <td>
              
  ```kotlin
    PickHourMinuteSecond(
        initialHour = hour,
        onHourChange = { hour = it },
        initialMinute = minute,
        onMinuteChange = { minute = it },
        initialSecond = second,
        onSecondChange = { second = it },
    )
  ```
  </td>
  <td>  
    <img src="https://raw.githubusercontent.com/anhaki/PickTime-Compose/refs/heads/main/assets/usage_3.gif" >           
  </td>
  </tr>
  <tr>
  <td>
              
  ```kotlin
    PickDate(
        initialDay = day,
        onDayChange = { day = it },
        initialMonth = month,
        onMonthChange = { month = it },
        initialYear = year,
        onYearChange = { year = it },
    )
  ```
  </td>
  <td>  
    <img src="https://raw.githubusercontent.com/anhaki/PickTime-Compose/refs/heads/main/assets/usage_4.gif" >           
  </td>
  </tr>
    <tr>
  <td>
              
  ```kotlin
    PickDate(
        initialDay = day,
        onDayChange = { day = it },
        initialMonth = month,
        onMonthChange = { month = it },
        initialYear = year,
        onYearChange = { year = it },
        monthList = listOf(
            "Enero", "Febrero", "Marzo", "Abril",
             "Mayo", "Junio", "Julio", "Agosto",
            "Septiembre", "Octubre", "Noviembre", "Diciembre"
        )
  ```
  </td>
  <td>  
    <img src="https://raw.githubusercontent.com/anhaki/PickTime-Compose/refs/heads/main/assets/usage_5.gif" >          
  </td>
  </tr>
</table>

---
## ✨ Features
- **Easy to Use**  
  Quickly implement hour, minute, second, and date pickers with just a few lines of code.

- **Highly Customizable**  
  - Fully customize text color, size, font family, and font weight using `PickTimeTextStyle`.
  - Adjust spacing between elements with `verticalSpace` and `horizontalSpace`.
  - Customize focus indicators with `PickTimeFocusIndicator`.
  - Switch between 24-hour and 12-hour formats using `timeFormat` (`TimeFormat.HOUR_12` / `TimeFormat.HOUR_24`).
  - Enable or disable looping (infinite scrolling) with the `isLooping` parameter.

- **Modern and Responsive Design**  
  - Easily style the picker for infinite possibility, including custom container backgrounds and focus indicator shapes.

- **Flexible for Different Use Cases**  
  - Pick only hour and minute (`PickHourMinute`).
  - Pick hour, minute, and second (`PickHourMinuteSecond`).
  - Pick a full date (`PickDate`) with optional (`monthList`) that can be used if different language is needed and (`yearRange`) for custom year range.

---

## 🛠️ Example of use

<table>
  <tr>
    <th>
      Code
    </th>
    <th>
      Preview
    </th>
  </tr>

  <tr>
  <td>
              
  ```kotlin
    var hour by remember { mutableIntStateOf(0) }
    var minute by remember { mutableIntStateOf(0) }

    PickHourMinute(
        initialHour = hour,
        onHourChange = { hour = it },
        initialMinute = minute,
        onMinuteChange = { minute = it },
        selectedTextStyle = PickTimeTextStyle(
            color = Color(0xFF29778E),
            fontSize = 26.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
        ),
        unselectedTextStyle = PickTimeTextStyle(
            color = Color(0xFF29778E),
            fontSize = 18.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
        ),
        verticalSpace = 10.dp,
        horizontalSpace = 15.dp,
        containerColor = Color.White,
        isLooping = true,
        extraRow = 1,
        focusIndicator = PickTimeFocusIndicator(
            enabled = true,
            widthFull = false,
            background = Color(0xFFCCEDF9),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(2.dp, Color(0xFF87CDE6)),
        )
    )
  ```
  </td>
  <td>  
    <img src="https://raw.githubusercontent.com/anhaki/PickTime-Compose/refs/heads/main/assets/eou_1.gif" >   
  </td>
  </tr>
  <tr>
  <td>
              
  ```kotlin
    var hour by remember { mutableIntStateOf(0) }
    var minute by remember { mutableIntStateOf(0) }

    PickHourMinute(
        initialHour = hour,
        onHourChange = { hour = it },
        initialMinute = minute,
        onMinuteChange = { minute = it },
        selectedTextStyle = PickTimeTextStyle(
            color = Color(0xFF5F5BAC),
            fontSize = 26.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
        ),
        unselectedTextStyle = PickTimeTextStyle(
            color = Color(0xFFAEABE3),
            fontSize = 26.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
        ),
        verticalSpace = 10.dp,
        horizontalSpace = 15.dp,
        containerColor = Color.White,
        isLooping = false,
        extraRow = 2,
        focusIndicator = PickTimeFocusIndicator(
            enabled = true,
            widthFull = true,
            background = Color(0xFFE1D8FF),
            shape = RectangleShape,
            border = BorderStroke(0.dp, Color(0xFF87CDE6)),
        )
    )
  ```
  </td>
  <td>  
    <img src="https://raw.githubusercontent.com/anhaki/PickTime-Compose/refs/heads/main/assets/eou_2.gif" >    
  </td>
  </tr>
  <tr>
  <td>
              
  ```kotlin
    var hour by remember { mutableIntStateOf(0) }
    var minute by remember { mutableIntStateOf(0) }

    PickHourMinute(
        initialHour = hour,
        onHourChange = { hour = it },
        initialMinute = minute,
        onMinuteChange = { minute = it },
        selectedTextStyle = PickTimeTextStyle(
            color = Color(0xFFFF0303),
            fontSize = 26.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
        ),
        unselectedTextStyle = PickTimeTextStyle(
            color = Color(0xFFF9DBBB),
            fontSize = 18.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
        ),
        timeFormat = TimeFormat.HOUR_12,
        verticalSpace = 5.dp,
        horizontalSpace = 5.dp,
        containerColor = Color(0xFF4E6E81),
        isLooping = true,
        extraRow = 2,
        focusIndicator = PickTimeFocusIndicator(
            enabled = true,
            widthFull = true,
            background = Color(0xFFF9DBBB),
            shape = RectangleShape,
            border = BorderStroke(0.dp, Color(0xFFF9DBBB)),
        )
    )
  ```
  </td>
  <td>  
    <img src="https://raw.githubusercontent.com/anhaki/PickTime-Compose/refs/heads/main/assets/eou_3.gif" >   
  </td>
  </tr>
  <tr>
  <td>
              
  ```kotlin
  var hour by remember { mutableIntStateOf(0) }
  var minute by remember { mutableIntStateOf(0) }
  var second by remember { mutableIntStateOf(0) }

  PickHourMinuteSecond(
      initialHour = hour,
      onHourChange = { hour = it },
      initialMinute = minute,
      onMinuteChange = { minute = it },
      initialSecond = second,
      onSecondChange = { second = it },
      selectedTextStyle = PickTimeTextStyle(
          color = Color(0xFF5A504B),
          fontSize = 26.sp,
          fontFamily = FontFamily.Default,
          fontWeight = FontWeight.Normal,
      ),
      unselectedTextStyle = PickTimeTextStyle(
          color = Color(0xFFBDB9B7),
          fontSize = 26.sp,
          fontFamily = FontFamily.Default,
          fontWeight = FontWeight.Normal,
      ),
      verticalSpace = 10.dp,
      horizontalSpace = 15.dp,
      containerColor = Color.White,
      isLooping = true,
      extraRow = 1,
      focusIndicator = PickTimeFocusIndicator(
          enabled = true,
          widthFull = false,
          background = Color(0xFFFFF2E9),
          shape = RoundedCornerShape(100.dp),
          border = BorderStroke(2.dp, Color(0xFFCE9468)),
      )
  )
  ```
  </td>
  <td>  
    <img src="https://raw.githubusercontent.com/anhaki/PickTime-Compose/refs/heads/main/assets/eou_4.gif" >   
  </td>
  </tr>
  <tr>
  <td>
              
  ```kotlin
  var hour by remember { mutableIntStateOf(0) }
  var minute by remember { mutableIntStateOf(0) }
  var second by remember { mutableIntStateOf(0) }

  PickHourMinuteSecond(
      initialHour = hour,
      onHourChange = { hour = it },
      initialMinute = minute,
      onMinuteChange = { minute = it },
      initialSecond = second,
      onSecondChange = { second = it },
      selectedTextStyle = PickTimeTextStyle(
          color = Color(0xFFFFDDAB),
          fontSize = 26.sp,
          fontFamily = FontFamily.Default,
          fontWeight = FontWeight.Bold,
      ),
      unselectedTextStyle = PickTimeTextStyle(
          color = Color(0xFFBFBD85),
          fontSize = 26.sp,
          fontFamily = FontFamily.Default,
          fontWeight = FontWeight.Bold,
      ),
      verticalSpace = 10.dp,
      horizontalSpace = 20.dp,
      containerColor = Color(0xFF5F8B4C),
      isLooping = false,
      extraRow = 2,
      focusIndicator = PickTimeFocusIndicator(
          enabled = true,
          widthFull = false,
          background = Color(0xFF945034),
          shape = RoundedCornerShape(100.dp),
          border = BorderStroke(2.dp, Color(0xFF945034)),
      )
  )
  ```
  </td>
  <td>  
    <img src="https://raw.githubusercontent.com/anhaki/PickTime-Compose/refs/heads/main/assets/eou_5.gif" >      
  </td>
  </tr>
  <tr>
  <td>
              
  ```kotlin
    var day by remember { mutableIntStateOf(28) }
    var month by remember { mutableIntStateOf(1) }
    var year by remember { mutableIntStateOf(2025) }
    
    PickDate(
      initialDay = day,
      onDayChange = { day = it },
      initialMonth = month,
      onMonthChange = { month = it },
      initialYear = year,
      onYearChange = { year = it },
      selectedTextStyle = PickTimeTextStyle(
          color = Color(0xFF4B8673),
          fontSize = 26.sp,
          fontFamily = FontFamily.Default,
          fontWeight = FontWeight.Bold,
      ),
      unselectedTextStyle = PickTimeTextStyle(
          color = Color(0xFF4B8673),
          fontSize = 18.sp,
          fontFamily = FontFamily.Default,
          fontWeight = FontWeight.Normal,
      ),
      verticalSpace = 10.dp,
      horizontalSpace = 15.dp,
      containerColor = Color(0xFFFFFFFF),
      isLooping = true,
      extraRow = 1,
      focusIndicator = PickTimeFocusIndicator(
          enabled = false,
      )
    )

  ```
  </td>
  <td>  
    <img src="https://raw.githubusercontent.com/anhaki/PickTime-Compose/refs/heads/main/assets/eou_6.gif" >   
  </td>
  </tr>
  <tr>
  <td>
              
  ```kotlin
    var day by remember { mutableIntStateOf(28) }
    var month by remember { mutableIntStateOf(1) }
    var year by remember { mutableIntStateOf(2025) }

    PickDate(
        initialDay = day,
        onDayChange = { day = it },
        initialMonth = month,
        onMonthChange = { month = it },
        initialYear = year,
        onYearChange = { year = it },
        monthList = listOf(
            "Janvier", "Février", "Mars", "Avril",
            "Mai", "Juin", "Juillet", "Août",
             "Septembre", "Octobre", "Novembre", "Décembre"
        ),
        yearRange = (2021..2030),
        selectedTextStyle = PickTimeTextStyle(
            color = Color(0xFFFFFFFF),
            fontSize = 26.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
        ),
        unselectedTextStyle = PickTimeTextStyle(
            color = Color(0xFF8B8C92),
            fontSize = 26.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
        ),
        verticalSpace = 10.dp,
        horizontalSpace = 10.dp,
        containerColor = Color(0xFF171925),
        isLooping = false,
        extraRow = 1,
        focusIndicator = PickTimeFocusIndicator(
            enabled = true,
            widthFull = false,
            background = Color(0xFFF21D8E),
            shape = RoundedCornerShape(15.dp),
            border = BorderStroke(0.dp, Color(0xFFF21D8E)),
        )
    )
  ```
  </td>
  <td>  
    <img src="https://raw.githubusercontent.com/anhaki/PickTime-Compose/refs/heads/main/assets/eou_7.gif" >   
  </td>
  </tr>

</table>

---

## 📄 License
This project is licensed under the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).

---

Made with ❤️ by [anhaki](https://github.com/anhaki)
