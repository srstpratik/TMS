from flask import request, jsonify
from .models import User
from .auth import require_role

@app.route('/api/data')
@require_role('Admin')  # Custom decorator to check user role
def get_sensitive_data():
    return jsonify({"data": "sensitive data"}) 