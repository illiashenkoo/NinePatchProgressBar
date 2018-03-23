# NinePatchProgressBar

<img src="https://raw.githubusercontent.com/illiashenkoo/NinePatchProgressBar/master/gif/screen.gif" width="320px" height="569px"/>

## Usage

### Step1：

```xml
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	dependencies {
	        compile 'com.github.illiashenkoo:NinePatchProgressBar:0.0.2'
	}

```


### Step2：

```xml
    <com.legacycle.ninepatchprogressbar.NinePatchProgressBar
        android:id="@+id/pb1"
        android:layout_width="match_parent"
        android:layout_height="16dp"
        app:np_bg_drawable="@drawable/np_bg"
        app:np_bg_padding_bottom="0dp"
        app:np_bg_padding_right="3dp"
        app:np_bg_padding_top="2dp"
        app:np_max="100"
        app:np_pb_drawable="@drawable/np_pb"
        app:np_progress="10"
        />
```