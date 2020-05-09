# Ide Helper

Database query supporter `ide-helper` package.

---

## Generate database support with ide-helper

This package related to database, help us to write compact code, and debugging query information.

- Installation

```bash
composer require --dev barryvdh/laravel-ide-helper
```

- Configure generation inside `composer.json`:

   ```bash
      "scripts":{
         "post-update-cmd": [
            "Illuminate\\Foundation\\ComposerScripts::postUpdate",
            "@php artisan ide-helper:generate",
            "@php artisan ide-helper:meta"
         ]
      },
   ```

- Add `ide-helper.php` to `config/` by running:

   ```bash
      php artisan vendor:publish --provider="Barryvdh\LaravelIdeHelper\IdeHelperServiceProvider" --tag=config
   ```

   - After that, change below lines to `true`:

   ```bash
      # automatic phpDocs generation
      'include_fluent' => true,

      # autocomplete for factory builder
      'include_factory_builders' => true,
   ```

   - Specifies models location for scanning:

   ```bash
      'model_locations' => array(
         'app/\Persistence/\Database/\Model',
      ),
   ```

- For laravel 5.5, after installed, manually add below class to `config/app.php` under `providers ` array:

   ```
   Barryvdh\LaravelIdeHelper\IdeHelperServiceProvider::class,
   ```

   - For newer laravel version, just register service at `app/Providers/AppServiceProvider.php`:

   ```bash
   public function register() {
      if ($this->app->environment() !== 'production') {
          $this->app->register(\Barryvdh\LaravelIdeHelper\IdeHelperServiceProvider::class);
      }
      // ...
   }
   ```

- Generate phpDoc for registered models:

   ```bash
      # should clear bootstrap/compiled.php first
      php artisan clear-compiled

      # generate phpDoc for registered models
      php artisan ide-helper:generate
   ```
